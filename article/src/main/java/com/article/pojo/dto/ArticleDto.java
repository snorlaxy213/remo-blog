package com.article.pojo.dto;

import lombok.Data;

@Data
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
     */
    private String publishDate;

    /**
     * 更新文章日期
     */
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
