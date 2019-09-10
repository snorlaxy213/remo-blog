package com.article.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto {
    private Integer id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章原作者
     */
    private String originalAuthor;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标签
     */
    private String articleTags;

    /**
     * 文章类型
     */
    private String articleType;

    /**
     * 文章分类
     */
    private String articleCategories;

    /**
     * 发布文章日期
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private String publishDate;

    /**
     * 更新文章日期
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private String updateDate;

    /**
     * 文章url
     */
    private String articleUrl;

    /**
     * 文章摘要
     */
    private String articleTabloid;

    /**
     * 文章喜欢数
     */
    private Integer likes;

    /**
     * 上一篇文章id
     */
    private Long lastArticleId;

    /**
     * 下一篇文章id
     */
    private Long nextArticleId;

    /**
     * 乐观锁
     */
    private Integer version;
}
