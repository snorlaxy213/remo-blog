package com.remo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private String publishDate;

    /**
     * 文章摘要
     */
    private String articleTabloid;

}
