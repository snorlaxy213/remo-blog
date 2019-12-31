package com.remo.pojo.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -1868394126029888027L;

    /**
     * 主键id
     */
    @TableId(value = "role_id", type = IdType.ID_WORKER)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 提示
     */
    @TableField("description")
    private String description;

    /**
     * 序号
     */
    @TableField("sort")
    private Integer sort;

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

    @Override
    public String toString() {
        return "Role{" +
        "roleId=" + roleId +
        ", name=" + name +
        ", description=" + description +
        ", sort=" + sort +
        ", version=" + version +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
