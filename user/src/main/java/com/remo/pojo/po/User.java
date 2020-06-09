package com.remo.pojo.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User实体类
 *
 * @author Jules
 * @date 2019/5/27
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1553854489109140483L;

    /**
     * 性别
     */
    public static final String SEX_MALE = "M";

    public static final String SEX_FEMALE = "F";

    public static final String SEX_UNKNOWN = "U";

    public static final String DEFAULT_PASSWORD = "1";

    /**
     * id
     * <p>
     * IdType：
     * IdType.AUTO: 主键自增 数据库中需要设置主键自增
     * IdType.NONE: 跟随全局策略走,全局里默认等于 INPUT
     * IdType.ASSIGN_UUID: UUID类型主键
     * IdType.ASSIGN_ID: 分配ID(主键类型为Number(Long和Integer)或String)
     * IdType.INPUT: insert前自行set主键值
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
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
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

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
