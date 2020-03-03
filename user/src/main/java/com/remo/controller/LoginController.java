package com.remo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.annotation.RemoLog;
import com.remo.common.constant.ErrorMessageConstant;
import com.remo.common.constant.RemoConstant;
import com.remo.common.domain.ActiveUser;
import com.remo.common.exception.exception.BusinessException;
import com.remo.common.properties.RemoProperties;
import com.remo.common.service.RedisService;
import com.remo.entity.JWTToken;
import com.remo.manager.UserManager;
import com.remo.pojo.dto.UserDto;
import com.remo.pojo.dto.query.LoginQuery;
import com.remo.pojo.vo.ResponseVo;
import com.remo.util.DateUtil;
import com.remo.util.IPUtil;
import com.remo.util.MD5Util;
import com.remo.util.ResponseUtil;
import com.remo.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "System security", value = "security api", protocols = "http")
public class LoginController {

    @Resource(name = "userManager")
    private UserManager userManager;

    @Resource(name = "remoProperties")
    private RemoProperties properties;

    @Resource(name = "redisServiceImpl")
    private RedisService redisService;

    @Autowired
    private ObjectMapper mapper;

    @ApiOperation(value = "login",
            notes = "check if userName and password is correct")
    @PostMapping("login")
    @RemoLog
    public ResponseVo login(@RequestBody LoginQuery query, HttpServletRequest request) throws Exception {

        String username = StringUtils.lowerCase(query.getUsername());
        String password = MD5Util.encrypt(query.getPassword());

        UserDto userDto = this.userManager.getUser(username);

        if (userDto == null) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.PASSWORD_ERROR);
        }
        if (!StringUtils.equals(userDto.getPassword(), password)) {
            throw new BusinessException(RemoConstant.ERROR_RESULT_CODE, ErrorMessageConstant.PASSWORD_ERROR);
        }

        Set<String> roles = this.userManager.getUserRoles(username);
        Set<String> permissions = this.userManager.getUserPermissions(username);

        String token = JwtTokenUtils.encryptToken(JwtTokenUtils.createToken(username, roles, permissions));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getSecurity().getJwtTimeOut());
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
        this.redisService.set(RemoConstant.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getSecurity().getJwtTimeOut() * 1000);

        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. 用户角色
     *
     * @param token token
     * @param userDto  用户信息
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
