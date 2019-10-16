package com.user.manager;


import com.user.common.service.CacheService;
import com.user.pojo.dto.UserDto;
import com.user.pojo.po.Role;
import com.user.pojo.po.User;
import com.user.service.RoleService;
import com.user.service.UserService;
import com.user.util.RemoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 封装一些和 User相关的业务操作
 */
@Service
public class UserManager {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 通过用户名获取用户基本信息
     *
     * @param username 用户名
     * @return 用户基本信息
     */
    public User getUser(String username) {
        return RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getUser(username),
                () -> this.userService.findUserByAccount(username));
    }

    /**
     * 通过用户名获取用户角色集合
     *
     * @param account 用户名
     * @return 角色集合
     */
    public Set<String> getUserRoles(String account) {
        List<Role> roleList = RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(account),
                () -> this.roleService.listUserRoles(account));
        return roleList.stream().map(Role::getName).collect(Collectors.toSet());
    }


    /**
     * 将用户相关信息添加到 Redis缓存中
     *
     * @param user user
     */
    public void loadUserRedisCache(User user) throws Exception {
        // 缓存用户
        cacheService.saveUser(user.getAccount());
        // 缓存用户角色
        cacheService.saveRoles(user.getAccount());
    }

    /**
     * 将用户角色和权限添加到 Redis缓存中
     *
     * @param userIds userIds
     */
    public void loadUserPermissionRoleRedisCache(List<Long> userIds) throws Exception {
        for (Long userId : userIds) {
            UserDto userDto = userService.findById(userId);
            // 缓存用户角色
            cacheService.saveRoles(userDto.getAccount());
        }
    }

    /**
     * 通过用户 id集合批量删除用户 Redis缓存
     *
     * @param userIds userIds
     */
    public void deleteUserRedisCache(Long... userIds) throws Exception {
        for (Long userId : userIds) {
            UserDto userDto = userService.findById(userId);
            if (userDto != null) {
                cacheService.deleteUser(userDto.getAccount());
                cacheService.deleteRoles(userDto.getAccount());
            }
        }
    }

}
