package com.remo.article.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleArticleDto {

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章类型
     */
    private String articleType;

    /**
     * 发布文章日期
     *
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
    private String publishDate;

    /**
     * 文章摘要
     */
    private String articleTabloid;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleTabloid() {
        return articleTabloid;
    }

    public void setArticleTabloid(String articleTabloid) {
        this.articleTabloid = articleTabloid;
    }
}
