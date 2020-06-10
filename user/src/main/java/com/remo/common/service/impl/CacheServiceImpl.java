package com.remo.common.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.common.constant.ErrorMessageConstant;
import com.remo.common.constant.RemoConstant;
import com.remo.common.exception.exception.BusinessException;
import com.remo.common.service.CacheService;
import com.remo.common.service.RedisService;
import com.remo.pojo.dto.PermissionDto;
import com.remo.pojo.dto.RoleDto;
import com.remo.pojo.dto.UserDto;
import com.remo.service.PermissionService;
import com.remo.service.RoleService;
import com.remo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Resource(name = "permissionService")
    PermissionService permissionService;
    @Resource(name = "redisService")
    private RedisService redisService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "userService")
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public UserDto getUser(String username) throws Exception {
        String userString = this.redisService.get(RemoConstant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.WRONG_INPUT);
        }
        else {
            return this.objectMapper.readValue(userString, UserDto.class);
        }
    }

    @Override
    public List<RoleDto> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(RemoConstant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.WRONG_INPUT);
        }
        else {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, RoleDto.class);
            return this.objectMapper.readValue(roleListString, type);
        }
    }

    @Override
    public List<PermissionDto> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new Exception();
        }
        else {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, PermissionDto.class);
            return this.objectMapper.readValue(permissionListString, type);
        }
    }

    @Override
    public void saveUser(UserDto user) throws Exception {
        String username = user.getUsername();
        this.deleteUser(username);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + username, objectMapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        UserDto user = userService.findByUsername(username);
        this.deleteUser(username);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + username, objectMapper.writeValueAsString(user));
    }

    @Override
    public void saveRoles(String username) throws Exception {
        List<RoleDto> roles = this.roleService.listUserRoles(username);
        if (!roles.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(RemoConstant.USER_ROLE_CACHE_PREFIX + username, objectMapper.writeValueAsString(roles));
        }
    }

    @Override
    public void savePermissions(String username) throws Exception {
        List<PermissionDto> permissions = this.permissionService.listUserPermissions(username);
        if (!permissions.isEmpty()) {
            this.deletePermissions(username);
            redisService.set(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username, objectMapper.writeValueAsString(permissions));
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
