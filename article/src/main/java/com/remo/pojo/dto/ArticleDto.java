package com.remo.pojo.dto;

import com.remo.validation.group.Update;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
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
     * 文章标签
     */
    private String articleTags;

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
     * 发布文章日期
     * @JsonFormat,注解@JsonFormat主要是后台到前台的时间格式的转换
     * @DataFormAT,注解@DataFormAT主要是前后到后台的时间格式的转换
     */
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String publishDate;

    /**
     * 更新文章日期
     *
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
    @NotNull(groups = Update.class)
    private Integer version;
}
