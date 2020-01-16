package com.remo.constants;

/**
 * @author shuang.kou
 */
public class SecurityConstants {

    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "remo.cache.token.";

    /**
     * 角色的key
     **/
    public static final String AUTHORITY_CLAIMS = "authority";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "role";

    /**
     * 过期时间是1个小时
     */
    public static final long EXPIRATION = 60L * 60L;

    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token defaults

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }
}
