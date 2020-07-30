package com.remo.userShiro.common.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.userShiro.common.domain.RemoConstant;
import com.remo.userShiro.common.exception.exception.BusinessException;
import com.remo.userShiro.common.service.CacheService;
import com.remo.userShiro.common.service.RedisService;
import com.remo.userShiro.pojo.dto.PermissionDto;
import com.remo.userShiro.pojo.dto.RoleDto;
import com.remo.userShiro.pojo.dto.UserDto;
import com.remo.userShiro.service.PermissionService;
import com.remo.userShiro.service.RoleService;
import com.remo.userShiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    PermissionService permissionService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public UserDto getUser(String username) throws Exception {
        String userString = this.redisService.get(RemoConstant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, RemoConstant.WRONG_INPUT);
        }
        else {
            return this.mapper.readValue(userString, UserDto.class);
        }
    }

    @Override
    public List<RoleDto> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(RemoConstant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, RemoConstant.WRONG_INPUT);
        }
        else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, RoleDto.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public List<PermissionDto> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new Exception();
        }
        else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, PermissionDto.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

    @Override
    public void saveUser(UserDto user) throws Exception {
        String username = user.getUsername();
        this.deleteUser(username);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        UserDto user = userService.findByUsername(username);
        this.deleteUser(username);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveRoles(String username) throws Exception {
        List<RoleDto> roles = this.roleService.listUserRoles(username);
        if (!roles.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(RemoConstant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(roles));
        }
    }

    @Override
    public void savePermissions(String username) throws Exception {
        List<PermissionDto> permissions = this.permissionService.findUserPermissions(username);
        if (!permissions.isEmpty()) {
            this.deletePermissions(username);
            redisService.set(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username, mapper.writeValueAsString(permissions));
        }
    }

    @Override
    public void deleteUser(String username) throws Exception {
        redisService.del(RemoConstant.USER_CACHE_PREFIX + username.toLowerCase());
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        redisService.del(RemoConstant.USER_ROLE_CACHE_PREFIX + username.toLowerCase());
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username);
    }
}
