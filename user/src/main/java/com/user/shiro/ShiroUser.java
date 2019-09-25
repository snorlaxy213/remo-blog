package com.user.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 *
 */
@Data
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 2275544723977673706L;

    /**
     * 用户主键ID
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色集
     */
    private List<Long> roleList;

    /**
     * 角色名称集
     */
    private List<String> roleNames;

}
