package com.remo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = -4821292542131014707L;

    /**
     * Tag Id,自增
     */
    @TableId(value = "tagId",type = IdType.AUTO)
    private Long tagId;

    /**
     * 简介
     */
    @TableField("tagDesc")
    private String tagDesc;

    /**
     * Tag 类型
     */
    @TableField("tagType")
    private String tagType;

    /**
     * 父Tag Id
     */
    @TableField("parentId")
    private Long parentId;

    /**
     * 排序编号
     */
    @TableField("orderSeq")
    private Integer orderSeq;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("createUser")
    private String createUser;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField("updateUser")
    private String updateUser;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
