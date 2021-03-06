package com.remo.article.pojo.vo.query;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ListArticleQuery {

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
     *
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
    private String publishDate;

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(String articleCategories) {
        this.articleCategories = articleCategories;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
