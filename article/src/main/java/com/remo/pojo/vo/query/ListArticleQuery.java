package com.remo.pojo.vo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
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
     * @JsonFormat,格式化传入的Date数据
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private String publishDate;

}
