package com.remo.gateway.build;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GatewayIPBlock {

    @Resource(name = "verificationBuild")
    private GatewayBuild gatewayBuild;

    public Boolean action(String ipAddress) {

        /**
         * 黑名单拦截
         */
        Boolean blackBlock = gatewayBuild.blackBlock(ipAddress);
        return blackBlock;

    }

}

