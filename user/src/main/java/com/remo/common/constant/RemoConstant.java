package com.remo.common.constant;

public class RemoConstant {

    // user缓存前缀
    public static final String USER_CACHE_PREFIX = "remo.cache.user.";
    // user角色缓存前缀
    public static final String USER_ROLE_CACHE_PREFIX = "remo.cache.user.role.";
    // user权限缓存前缀
    public static final String USER_PERMISSION_CACHE_PREFIX = "remo.cache.user.permission.";
    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "remo.cache.token.";
    // 存储在线用户的 zset前缀
    public static final String ACTIVE_USERS_ZSET_PREFIX = "remo.user.active";

    // HTTP状态码
    public final static Integer ERROR_RESULT_CODE = 1;
    public final static Integer SUCCESS_RESULT_CODE = 0;
}
