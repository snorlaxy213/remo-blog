package com.user.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.common.domain.ActiveUser;
import com.user.common.domain.RemoConstant;
import com.user.common.service.RedisService;
import com.user.constant.BusinessConstants;
import com.user.exception.exception.BusinessException;
import com.user.manager.UserManager;
import com.user.pojo.po.User;
import com.user.pojo.vo.LoginVo;
import com.user.pojo.vo.ResponseVo;
import com.user.properties.RemoProperties;
import com.user.shiro.JWTToken;
import com.user.shiro.JWTUtil;
import com.user.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseVo login(@RequestBody LoginVo loginVo, HttpServletRequest request)  throws Exception {
        String account = loginVo.getUserName();
        String password = loginVo.getPassword();

        account = StringUtils.lowerCase(account);
        password = MD5Util.encrypt(account, password);

        final String errorMessage = "用户名或密码错误";
        User user = this.userManager.getUser(account);

        if (user == null)
            throw new BusinessException(BusinessConstants.ERROR_RESULT_CODE,errorMessage);
        if (!StringUtils.equals(user.getPassword(), password))
            throw new BusinessException(BusinessConstants.ERROR_RESULT_CODE,errorMessage);

        String token = RemoUtil.encryptToken(JWTUtil.sign(account, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        String userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setUserId(Long.valueOf(userId));

        Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
        return ResponseUtil.initSuccessResultVO(userInfo);
    }

    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getAccount());
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
     * 2. Vue Router
     * 3. 用户角色
     * 4. 用户权限
     * 5. 前端系统个性化配置信息
     *
     * @param token token
     * @param user  用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
        String account = user.getAccount();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("expireTime", token.getExpireAt());

        Set<String> roles = this.userManager.getUserRoles(account);
        userInfo.put("roles", roles);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }
}
