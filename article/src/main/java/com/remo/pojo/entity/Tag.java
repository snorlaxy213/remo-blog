package com.remo.pojo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class Tag implements Serializable {

    private static final long serialVersionUID = -4821292542131014707L;

    /**
     * Tag Id,自增
     */
    @TableId(value = "tag_id",type = IdType.AUTO)
    private Long tagId;

    /**
     * 简介
     */
    @TableField("desc")
    private String Desc;

    /**
     * 父Tag Id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序编号
     */
    @TableField("order_seq")
    private Integer orderSeq;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
