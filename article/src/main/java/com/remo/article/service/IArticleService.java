package com.remo.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.article.pojo.dto.ArticleDto;
import com.remo.article.pojo.dto.SimpleArticleDto;
import com.remo.article.pojo.entity.Article;
import com.remo.article.pojo.vo.query.ListArticleQuery;

import java.time.LocalDate;
import java.util.List;

/**
 * Article服务类
 *
 * @author vino
 * @since 2019-08-26
 */
public interface IArticleService extends IService<Article> {

    List<ArticleDto> listArticles(ListArticleQuery query);

    List<SimpleArticleDto> listSimpleArticles();

    List<SimpleArticleDto> articleRanks(LocalDate from, LocalDate to);

    ArticleDto getArticle(Long id);

    boolean insertArticle(ArticleDto articleDto);

    boolean updateArticle(ArticleDto articleDto);

}
