package com.remo.gateway.build;

/**
 * description: 网关行为建造者
 */
public interface GatewayBuild {

    /**
     * 黑名单拦截
     *
     * @param ipAddress IP地址
     */
    Boolean blackBlock(String ipAddress);

}
