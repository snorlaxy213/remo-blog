package com.remo.article.pojo.dto;

import com.remo.basic.group.Insert;
import com.remo.basic.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TagDto {

    /**
     * articleDtos
     */
    List<ArticleDto> articleDtos;
    /**
     * Tag Id
     */
    @NotNull(groups = Update.class)
    private Long tagId;
    /**
     * 简介
     */
    @NotBlank(groups = Insert.class)
    private String tagDescription;
    /**
     * 排序编号
     */
    @NotNull(groups = Insert.class)
    private Integer orderNum;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<ArticleDto> getArticleDtos() {
        return articleDtos;
    }

    public void setArticleDtos(List<ArticleDto> articleDtos) {
        this.articleDtos = articleDtos;
    }
}
