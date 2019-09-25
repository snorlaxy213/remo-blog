package com.user.shiro;

import com.user.pojo.po.User;

/**
 * shiro工具类
 *
 */
public class ShiroKit {

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 1024;

    /**
     * 通过用户表的信息创建一个shiroUser对象
     */
    public static ShiroUser createShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        if (user == null) {
            return shiroUser;
        }

        shiroUser.setId(user.getUserId());
        shiroUser.setAccount(user.getAccount());
        shiroUser.setName(user.getName());
        shiroUser.setEmail(user.getEmail());
        shiroUser.setAvatar(user.getAvatar());

        return shiroUser;
    }
}
