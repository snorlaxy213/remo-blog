package com.remo.service;

import com.remo.pojo.dto.ArticleDto;
import com.remo.pojo.dto.SimpleArticleDto;
import com.remo.pojo.entity.Article;
import com.remo.pojo.vo.query.ListArticleQuery;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *  Article服务类
 *
 * @author vino
 * @since 2019-08-26
 */
public interface IArticleService extends IService<Article> {

    List<ArticleDto> listArticles(ListArticleQuery query);

    List<SimpleArticleDto> listSimpleArticles();

    ArticleDto getArticle(Long id);

    boolean insertArticle(ArticleDto articleDto);

    boolean updateArticle(ArticleDto articleDto);

}
