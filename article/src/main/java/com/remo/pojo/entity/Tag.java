package com.remo.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableField("tag_description")
    private String tagDescription;

    /**
     * 排序编号
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 是否删除:1-以删除;0-存在
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;
}
