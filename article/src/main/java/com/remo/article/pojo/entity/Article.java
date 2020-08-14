package com.remo.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

@TableName("article")
public class Article extends Base implements Serializable {

    private static final long serialVersionUID = 8365686186585051797L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id", fill = FieldFill.INSERT)
    private Long articleId;

    /**
     * 作者
     */
    @TableField(value = "author_name")
    private String authorName;

    /**
     * 文章原作者
     */
    @TableField("original_author_name")
    private String originalAuthorName;

    /**
     * 文章标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 文章内容
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * 文章类型
     */
    @TableField("article_type")
    private String articleType;

    /**
     * 文章url
     */
    @TableField("article_url")
    private String articleUrl;

    /**
     * 文章摘要
     */
    @TableField("article_tabloid")
    private String articleTabloid;

    /**
     * 文章喜欢数
     */
    @TableField("likes")
    private Integer likes;

    /**
     * 上一篇文章id
     */
    @TableField("last_article_id")
    private Long lastArticleId;

    /**
     * 下一篇文章id
     */
    @TableField("next_article_id")
    private Long nextArticleId;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getOriginalAuthorName() {
        return originalAuthorName;
    }

    public void setOriginalAuthorName(String originalAuthorName) {
        this.originalAuthorName = originalAuthorName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleTabloid() {
        return articleTabloid;
    }

    public void setArticleTabloid(String articleTabloid) {
        this.articleTabloid = articleTabloid;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getLastArticleId() {
        return lastArticleId;
    }

    public void setLastArticleId(Long lastArticleId) {
        this.lastArticleId = lastArticleId;
    }

    public Long getNextArticleId() {
        return nextArticleId;
    }

    public void setNextArticleId(Long nextArticleId) {
        this.nextArticleId = nextArticleId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
