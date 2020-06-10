package com.remo.pojo.dto;

import com.remo.common.validation.group.Insert;
import com.remo.common.validation.group.Update;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
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
}
