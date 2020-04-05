package com.remo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 7187628714679791771L;

    @TableId(value = "permission_id", type = IdType.ID_WORKER)
    private Long permissionId;

    @TableField("permission_name")
    private String permissionName;

    @TableField("path")
    private String path;

    @TableField("type")
    private String type;

    @TableField("parent_id")
    private Long parentId;

    @TableField("order_num")
    private Integer orderNum;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建用户
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 修改用户
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

}