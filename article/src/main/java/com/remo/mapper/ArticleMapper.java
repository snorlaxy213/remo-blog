package com.remo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.pojo.entity.Article;

/**
 * Article Mapper
 *
 * @author vino
 * @since 2019-08-26
 */
public interface ArticleMapper extends BaseMapper<Article> {

    int deleteResolutionByArticleId(Long articleId);

    int addResolutionWithArticleAndTag(Long articleId, Long tagId);

}
