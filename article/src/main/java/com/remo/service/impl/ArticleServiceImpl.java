package com.remo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.exception.exception.ParamException;
import com.remo.mapper.ArticleMapper;
import com.remo.pojo.dto.ArticleDto;
import com.remo.pojo.dto.FieldErrorDto;
import com.remo.pojo.dto.SimpleArticleDto;
import com.remo.pojo.entity.Article;
import com.remo.pojo.vo.query.ListArticleQuery;
import com.remo.service.IArticleService;
import com.remo.utils.ServiceUtil;
import com.remo.utils.constant.FormatConstant;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  文章服务实现
 *
 * @author vino
 * @since 2019-08-26
 */
@Slf4j
@Service("articleServiceImpl")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    @Qualifier("dozerBeanMapper")
    DozerBeanMapper dozerMapper;

    /**
     * 根据ListArticleQuery的参数查询Articles
     * @param query
     * @return
     */
    @Override
//    @DS("slave")
    public List<ArticleDto> listArticles(ListArticleQuery query) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (query.getArticleType() != null) {
            wrapper.eq("articleType", query.getArticleType());
        }
        if (query.getArticleTags() != null) {
            wrapper.eq("articleTags", query.getArticleTags());
        }
        if (query.getArticleCategories() != null) {
            wrapper.eq("articleCategories", query.getArticleCategories());
        }
        if (query.getPublishDate() != null) {
            wrapper.eq("publishDate", query.getPublishDate());
        }

        List<Article> articles = this.list(wrapper);
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article -> articleDtos.add(dozerMapper.map(article,ArticleDto.class)));
        return articleDtos;
    }

    @Override
    public List<SimpleArticleDto> listSimpleArticles(){
        List<Article> articles = this.list();
        List<SimpleArticleDto> simpleArticleDtos = new ArrayList<>();
        articles.forEach(article -> simpleArticleDtos.add(dozerMapper.map(article,SimpleArticleDto.class)));
        return simpleArticleDtos;
    }

    @Override
    public ArticleDto getArticle(Long id) {
        Article article = this.getById(id);
        return dozerMapper.map(article, ArticleDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertArticle(ArticleDto articleDto) {
        //validation
        List<FieldErrorDto> fieldErrorDtos = new ArrayList<>();
        try {
            FormatConstant.yyyyMM.parse(articleDto.getPublishDate());
        } catch (ParseException e) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto("publishDate","date format error");
            fieldErrorDtos.add(fieldErrorDto);
        }

        if (!fieldErrorDtos.isEmpty()) {
            throw new ParamException(fieldErrorDtos);
        }

        //service business
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        ServiceUtil.initEntity(article,true);
        boolean flag = this.save(article);

        articleDto.getTagDtos().forEach(tagDto -> {
            this.getBaseMapper().addResolutionWithArticleAndTag(article.getArticleId(), tagDto.getTagId());
        });

        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(ArticleDto articleDto) {
        //validation
        List<FieldErrorDto> fieldErrorDtos = new ArrayList<>();
        try {
            FormatConstant.yyyyMM.parse(articleDto.getUpdateDate());
        } catch (ParseException e) {
            FieldErrorDto fieldErrorDto = new FieldErrorDto("publishDate","date format error");
            fieldErrorDtos.add(fieldErrorDto);
        }

        if (!fieldErrorDtos.isEmpty()) {
            throw new ParamException(fieldErrorDtos);
        }

        //service business
        Article article = this.getById(articleDto.getId());

        article.setArticleTitle(articleDto.getArticleTitle())
                .setArticleContent(articleDto.getArticleContent())
                .setArticleTags(articleDto.getArticleTags())
                .setArticleType(articleDto.getArticleType())
                .setArticleCategories(articleDto.getArticleCategories())
                .setUpdateDate(FormatConstant.yyyyMMdd.format(new Date()))
                .setArticleTabloid(articleDto.getArticleTabloid())
                .setVersion(articleDto.getVersion());

        ServiceUtil.initEntity(article,false);
        boolean flag = this.updateById(article);
        if(flag){
            log.info("Update successfully");
        }else{
            log.info("Update failed due to modified by others");
        }

        //update article-tag relation
        this.getBaseMapper().deleteResolutionByArticleId(article.getArticleId());
        articleDto.getTagDtos().forEach(tagDto ->
            this.getBaseMapper().addResolutionWithArticleAndTag(article.getArticleId(), tagDto.getTagId())
        );

        return flag;
    }

}
