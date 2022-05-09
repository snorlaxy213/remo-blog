package com.remo.user.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.auth.entity.JWTToken;
import com.remo.auth.utils.JwtTokenUtils;
import com.remo.user.common.constant.RemoConstant;
import com.remo.user.common.domain.ActiveUser;
import com.remo.user.common.properties.RemoProperties;
import com.remo.user.common.service.RedisService;
import com.remo.user.manager.UserManager;
import com.remo.user.pojo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component("loginHelper")
public class LoginHelper {

    @Autowired
    HttpServletRequest request;

    @Resource(name = "remoProperties")
    private RemoProperties properties;

    @Autowired
    private ObjectMapper objectMapper;

    @Resource(name = "userManager")
    private UserManager userManager;

    @Resource(name = "redisService")
    private RedisService redisService;

    /**
     * 根据userDto组装返回的前端使用的用户信息
     * @param userDto userDto
     * @return 前端使用的用户信息
     * @throws Exception JSON解析异常、Redis连接异常
     */
    public Map<String, Object> getUserInfo(UserDto userDto) throws Exception {
        String username = userDto.getUsername();

        //添加默认角色
        Set<String> roles = this.userManager.getUserRoles(username);

        Set<String> permissions = this.userManager.getUserPermissions(username);

        String token = JwtTokenUtils.encryptToken(JwtTokenUtils.createToken(username, roles, permissions));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getSecurity().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        String userId = this.saveTokenToRedis(userDto, jwtToken, request);
        userDto.setUserId(Long.valueOf(userId));

        return this.generateUserInfo(jwtToken, userDto);
    }

    /**
     * 把token保存进Redis
     * @param user 用户
     * @param token token
     * @param request http request
     * @return 用户id
     * @throws Exception JSON解析异常、Redis连接异常
     */
    private String saveTokenToRedis(UserDto user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);
        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(RemoConstant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExpireAt()), objectMapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(RemoConstant.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getSecurity().getJwtTimeOut() * 1000);

        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. 用户角色
     *
     * @param token   token
     * @param userDto 用户信息
     * @return token、token过期时间、用户信息、角色
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
