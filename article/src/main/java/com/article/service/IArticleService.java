package com.article.service;

import com.article.pojo.dto.ArticleDto;
import com.article.pojo.entity.Article;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  Article服务类
 * </p>
 *
 * @author vino
 * @since 2019-08-26
 */
public interface IArticleService extends IService<Article> {

    List<ArticleDto> listArticles();

    ArticleDto getArticle(Long id);

    boolean insertArticle(ArticleDto articleDto);

    boolean updateArticle(ArticleDto articleDto);

}
