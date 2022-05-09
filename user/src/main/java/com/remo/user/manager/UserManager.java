package com.remo.user.manager;


import com.remo.user.common.service.CacheService;
import com.remo.user.pojo.dto.PermissionDto;
import com.remo.user.pojo.dto.RoleDto;
import com.remo.user.pojo.dto.UserDto;
import com.remo.user.service.PermissionService;
import com.remo.user.service.RoleService;
import com.remo.user.service.UserService;
import com.remo.user.utils.RemoUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userManager")
public class UserManager {

    @Resource(name = "cacheService")
    private CacheService cacheService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "permissionService")
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
        Set<String> roleNames = new HashSet<>();
        //根据用户名查询角色
        List<RoleDto> roles = RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.listUserRoles(username));
        if (CollectionUtils.isEmpty(roles)) {
            roleNames.add("1");
        } else {
            roleNames = roles.stream()
                    .map(RoleDto::getName)
                    .collect(Collectors.toSet());
        }
        return roleNames;
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    public Set<String> getUserPermissions(String username) {
        Set<String> permissionNames = new HashSet<>();
        //根据用户名查询权限
        List<PermissionDto> permissions = RemoUtil.selectCacheByTemplate(
                () -> this.cacheService.getPermissions(username),
                () -> this.permissionService.listUserPermissions(username));
        //如果角色为空，添加默认角色
        if (CollectionUtils.isEmpty(permissions)) {
            permissionNames.add("1");
        } else {
            permissionNames = permissions.stream()
                    .map(PermissionDto::getPermissionName)
                    .collect(Collectors.toSet());
        }
        return permissionNames;
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
