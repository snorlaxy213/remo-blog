package com.user.common.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.common.domain.RemoConstant;
import com.user.common.service.CacheService;
import com.user.common.service.RedisService;
import com.user.constant.BusinessConstant;
import com.user.constant.ViewConstants;
import com.user.exception.exception.BusinessException;
import com.user.pojo.dto.PermissionDto;
import com.user.pojo.dto.RoleDto;
import com.user.pojo.dto.UserDto;
import com.user.service.PermissionService;
import com.user.service.RoleService;
import com.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public UserDto getUser(String username) throws Exception {
        String userString = this.redisService.get(RemoConstant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString)){
            throw new BusinessException(BusinessConstant.ERROR_RESULT_CODE, ViewConstants.WRONG_INPUT);
        } else {
            return this.mapper.readValue(userString, UserDto.class);
        }
    }

    @Override
    public List<RoleDto> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(RemoConstant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new BusinessException(BusinessConstant.ERROR_RESULT_CODE, ViewConstants.WRONG_INPUT);
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, RoleDto.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public List<PermissionDto> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(RemoConstant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new Exception();
        } else {
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
