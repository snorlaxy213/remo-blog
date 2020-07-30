package com.remo.userShiro.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.userShiro.common.domain.ActiveUser;
import com.remo.userShiro.common.domain.RemoConstant;
import com.remo.userShiro.common.exception.exception.BusinessException;
import com.remo.userShiro.common.properties.RemoProperties;
import com.remo.userShiro.common.service.RedisService;
import com.remo.userShiro.manager.UserManager;
import com.remo.userShiro.pojo.dto.UserDto;
import com.remo.userShiro.pojo.vo.ResponseVo;
import com.remo.userShiro.shiro.JWTToken;
import com.remo.userShiro.shiro.JWTUtil;
import com.remo.userShiro.utils.*;
import com.remo.userShiro.validation.groups.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "System security")
public class LoginController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private RemoProperties properties;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "login", notes = "check if userName and password is correct")
    @PostMapping("login")
    public ResponseVo login(@Validated(Login.class) @RequestBody UserDto loginDto, HttpServletRequest request) throws Exception {
        String username = StringUtils.lowerCase(loginDto.getUsername());
        String password = MD5Util.encrypt(username, loginDto.getPassword());
        final String errorMessage = "用户名或密码错误";

        UserDto userDto = this.userManager.getUser(username);

        if (userDto == null) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, errorMessage);
        }
        if (!StringUtils.equals(userDto.getPassword(), password)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, errorMessage);
        }

        String token = RemoUtil.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        String userId = this.saveTokenToRedis(userDto, jwtToken, request);
        userDto.setUserId(Long.valueOf(userId));

        Map<String, Object> userInfo = this.generateUserInfo(jwtToken, userDto);
        return ResponseUtil.initSuccessResultVO(userInfo);
    }

    private String saveTokenToRedis(UserDto user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(RemoConstant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExpireAt()), mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(RemoConstant.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getShiro().getJwtTimeOut() * 1000);

        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. 用户角色
     *
     * @param token   token
     * @param userDto 用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, UserDto userDto) {
        String username = userDto.getUsername();
        Map<String, Object> userInfo = new HashMap<>(5);
        userInfo.put("token", token.getToken());
        userInfo.put("expireTime", token.getExpireAt());

        Set<String> roles = this.userManager.getUserRoles(username);
        userInfo.put("roles", roles);

        userDto.setPassword(null);
        userInfo.put("user", userDto);
        return userInfo;
    }
}
