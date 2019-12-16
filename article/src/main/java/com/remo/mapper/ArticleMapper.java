package com.remo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.pojo.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

/**
 * Article Mapper
 *
 * @author vino
 * @since 2019-08-26
 */
public interface ArticleMapper extends BaseMapper<Article> {

    @Delete("delete from article_tag where articleId = #{articleId}")
    int deleteResolutionByArticleId(Long articleId);

    @Insert("insert into article_tag values (#{articleId}, #{tagId})")
    int addResolutionWithArticleAndTag(Long articleId, Long tagId);

}
