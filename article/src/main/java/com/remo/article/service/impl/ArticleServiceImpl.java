package com.remo.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.article.common.util.ServiceUtil;
import com.remo.article.mapper.ArticleMapper;
import com.remo.article.pojo.dto.ArticleDto;
import com.remo.article.pojo.dto.SimpleArticleDto;
import com.remo.article.pojo.entity.Article;
import com.remo.article.pojo.vo.query.ListArticleQuery;
import com.remo.article.service.IArticleService;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * 文章服务实现
 *
 * @author vino
 * @since 2019-08-26
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    public static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Resource
    @Qualifier("dozerBeanMapper")
    DozerBeanMapper dozerMapper;

    /**
     * 根据ListArticleQuery的参数查询Articles
     *
     * @param listArticleQuery
     * @return
     */
    @Override
    public List<ArticleDto> listArticles(ListArticleQuery listArticleQuery) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (listArticleQuery.getArticleType() != null) {
            wrapper.eq("articleType", listArticleQuery.getArticleType());
        }
        if (listArticleQuery.getArticleTags() != null) {
            wrapper.eq("articleTags", listArticleQuery.getArticleTags());
        }
        if (listArticleQuery.getArticleCategories() != null) {
            wrapper.eq("articleCategories", listArticleQuery.getArticleCategories());
        }
        if (listArticleQuery.getPublishDate() != null) {
            wrapper.eq("publishDate", listArticleQuery.getPublishDate());
        }

        //组装Page条件
        IPage<Article> page = new Page<>(listArticleQuery.getCurrentPage(), listArticleQuery.getPageSize()); 
        IPage<Article> articlesPage = this.page(page, wrapper);
        List<Article> articles = articlesPage.getRecords();
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article -> articleDtos.add(dozerMapper.map(article, ArticleDto.class)));
        return articleDtos;
    }

    /**
     * 展示多条文章及其相关简介
     * @return 文章及其相关简介
     */
    @Override
    public List<SimpleArticleDto> listSimpleArticles() {
        List<Article> articles = this.list();
        List<SimpleArticleDto> simpleArticleDtos = new ArrayList<>();
        articles.forEach(article -> simpleArticleDtos.add(dozerMapper.map(article, SimpleArticleDto.class)));
        return simpleArticleDtos;
    }

    @Override
    public List<SimpleArticleDto> articleRanks(LocalDate from, LocalDate to) {

        return null;
    }

    @Override
    public ArticleDto getArticle(Long id) {
        Article article = this.getById(id);
        return dozerMapper.map(article, ArticleDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        ServiceUtil.initEntity(article, true);
        boolean flag = this.save(article);

        articleDto.getTagDtos().forEach(tagDto -> {
            this.getBaseMapper().addResolutionWithArticleAndTag(article.getArticleId(), tagDto.getTagId());
        });

        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(ArticleDto articleDto) {
        Article article = this.getById(articleDto.getId());

        article.setArticleTitle(articleDto.getArticleTitle());
        article.setArticleContent(articleDto.getArticleContent());
        article.setArticleType(articleDto.getArticleType());
        article.setUpdateTime(LocalDateTime.now());
        article.setArticleTabloid(articleDto.getArticleTabloid());
        article.setVersion(articleDto.getVersion());

        ServiceUtil.initEntity(article, false);
        boolean flag = this.updateById(article);
        if (flag) {
            log.info("Update successfully");
        } else {
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
