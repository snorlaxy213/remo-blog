package com.remo.pojo.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * User实体类
 *
 * @author Jules
 * @date 2019/5/27
 */
@Data
@TableName("sys_user")
public class User implements Serializable {

    /**
     * 性别
     */
    public static final String SEX_MALE = "0";
    public static final String SEX_FEMALE = "1";
    public static final String SEX_UNKNOWN = "2";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1";
    private static final long serialVersionUID = 1553854489109140483L;
    /**
     * id
     */
    @TableId(value = "user_id", type = IdType.ID_WORKER)
    private Long userId;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 账户
     */
    @TableField("username")
    private String username;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 移动号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 电子邮件
     */
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别(字典)
     */
    @TableField("gender")
    private String gender;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("birthday")
    private String birthday;

    /**
     * 个人简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 角色id(多个逗号隔开)
     */
    @TableField("role_id")
    private String roleId;

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
     * 乐观锁
     */
    @TableField("version")
    private Integer version;
}
