package com.remo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TagDto {

    /**
     * Tag Id
     */
    private Long tagId;

    /**
     * 简介
     */
    private String Desc;

    /**
     * 父Tag Id
     */
    private Long parentId;

    /**
     * 排序编号
     */
    private Integer orderDeq;
}
