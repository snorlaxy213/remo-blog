package com.user.manager;


import com.user.common.service.CacheService;
import com.user.pojo.dto.PermissionDto;
import com.user.pojo.dto.RoleDto;
import com.user.pojo.dto.UserDto;
import com.user.service.PermissionService;
import com.user.service.RoleService;
import com.user.service.UserService;
import com.user.util.RemoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserManager {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 通过用户名获取用户基本信息
     *
     * @param username 用户名
     * @return 用户基本信息
     */
    public UserDto getUser(String username) {
        return RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getUser(username),
                () -> this.userService.findByUsername(username));
    }

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    public Set<String> getUserRoles(String username) {
        List<RoleDto> roleList = RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.listUserRoles(username));
        return roleList.stream().map(RoleDto::getName).collect(Collectors.toSet());
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    public Set<String> getUserPermissions(String username) {
        List<PermissionDto> roleList = RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getPermissions(username),
                () -> this.permissionService.findUserPermissions(username));
        return roleList.stream().map(PermissionDto::getPermissionName).collect(Collectors.toSet());
    }

    /**
     * 将用户相关信息添加到 Redis缓存中
     *
     * @param username username
     */
    public void loadUserRedisCache(String username) throws Exception {
        // 缓存用户
        cacheService.saveUser(username);
        // 缓存用户角色
        cacheService.saveRoles(username);
        // 缓存用户权限
        cacheService.savePermissions(username);
    }

    /**
     * 将用户角色和权限添加到 Redis缓存中
     *
     * @param userIds userIds
     */
    public void loadUserRoleRedisCache(List<Long> userIds) throws Exception {
        for (Long userId : userIds) {
            UserDto userDto = userService.findById(userId);
            // 缓存用户角色
            cacheService.saveRoles(userDto.getUsername());
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
                cacheService.deleteUser(userDto.getUsername());
                cacheService.deleteRoles(userDto.getUsername());
            }
        }
    }

}
