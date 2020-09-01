package com.remo.article.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.remo.basic.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto {

    @NotNull(groups = Update.class)
    private Integer id;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 作者
     */
    @NotEmpty
    private String author;
    /**
     * 文章原作者
     */
    private String originalAuthor;
    /**
     * 文章标题
     */
    @NotBlank
    private String articleTitle;
    /**
     * 文章内容
     */
    @NotBlank
    private String articleContent;
    /**
     * 文章类型
     */
    @NotBlank
    private String articleType;
    /**
     * 文章分类
     */
    private String articleCategories;
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
    @NotNull(groups = Update.class)
    private Integer version;

    /**
     * TagDtos
     */
    List<TagDto> tagDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
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

    public String getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(String articleCategories) {
        this.articleCategories = articleCategories;
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

    public List<TagDto> getTagDtos() {
        return tagDtos;
    }

    public void setTagDtos(List<TagDto> tagDtos) {
        this.tagDtos = tagDtos;
    }
}
