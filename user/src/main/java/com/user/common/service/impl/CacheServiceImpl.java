package com.user.common.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.common.domain.RemoConstant;
import com.user.common.service.CacheService;
import com.user.common.service.RedisService;
import com.user.mapper.UserMapper;
import com.user.pojo.po.Role;
import com.user.pojo.po.User;
import com.user.service.RoleService;
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
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String account) throws Exception {
        String userString = this.redisService.get(RemoConstant.USER_CACHE_PREFIX + account);
        if (StringUtils.isBlank(userString))
            throw new Exception();
        else
            return this.mapper.readValue(userString, User.class);
    }

    @Override
    public List<Role> getRoles(String account) throws Exception {
        String roleListString = this.redisService.get(RemoConstant.USER_ROLE_CACHE_PREFIX + account);
        if (StringUtils.isBlank(roleListString)) {
            throw new Exception();
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public void saveUser(User user) throws Exception {
        String account = user.getAccount();
        this.deleteUser(account);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + account, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String account) throws Exception {
        User user = userMapper.findByAccount(account);
        this.deleteUser(account);
        redisService.set(RemoConstant.USER_CACHE_PREFIX + account, mapper.writeValueAsString(user));
    }

    @Override
    public void saveRoles(String account) throws Exception {
        List<Role> roleList = this.roleService.listUserRoles(account);
        if (!roleList.isEmpty()) {
            this.deleteRoles(account);
            redisService.set(RemoConstant.USER_ROLE_CACHE_PREFIX + account, mapper.writeValueAsString(roleList));
        }
    }

    @Override
    public void deleteUser(String account) throws Exception {
        account = account.toLowerCase();
        redisService.del(RemoConstant.USER_CACHE_PREFIX + account);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(RemoConstant.USER_ROLE_CACHE_PREFIX + username);
    }
}
