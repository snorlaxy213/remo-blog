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
        return this.save(article);
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

        //文章标题
        article.setArticleTitle(articleDto.getArticleTitle());
        //文章内容
        article.setArticleContent(articleDto.getArticleContent());
        //文章标签
        article.setArticleTags(articleDto.getArticleTags());
        //文章类型
        article.setArticleType(articleDto.getArticleType());
        //文章分类
        article.setArticleCategories(articleDto.getArticleCategories());
        //更新文章日期
        article.setUpdateDate(FormatConstant.yyyyMMdd.format(new Date()));
        //文章摘要
        article.setArticleTabloid(articleDto.getArticleTabloid());
        //乐观锁
        article.setVersion(articleDto.getVersion());

        ServiceUtil.initEntity(article,false);
        boolean flag = this.updateById(article);
        //记录日志
        if(flag){

            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }
        return flag;
    }

}
