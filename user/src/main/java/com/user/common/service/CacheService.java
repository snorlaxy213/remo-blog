package com.user.common.service;


import com.user.pojo.po.Role;
import com.user.pojo.po.User;

import java.util.List;

public interface CacheService {

    /**
     * 测试 Redis是否连接成功
     */
    void testConnect() throws Exception;

    /**
     * 从缓存中获取用户
     *
     * @param username 用户名
     * @return User
     */
    User getUser(String username) throws Exception;

    /**
     * 从缓存中获取用户角色
     *
     * @param username 用户名
     * @return 角色集
     */
    List<Role> getRoles(String username) throws Exception;

    /**
     * 缓存用户信息，只有当用户信息是查询出来的，完整的，才应该调用这个方法
     * 否则需要调用下面这个重载方法
     *
     * @param user 用户信息
     */
    void saveUser(User user) throws Exception;

    /**
     * 缓存用户信息
     *
     * @param username 用户名
     */
    void saveUser(String username) throws Exception;

    /**
     * 缓存用户角色信息
     *
     * @param username 用户名
     */
    void saveRoles(String username) throws Exception;

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    void deleteUser(String username) throws Exception;

    /**
     * 删除用户角色信息
     *
     * @param username 用户名
     */
    void deleteRoles(String username) throws Exception;

}
