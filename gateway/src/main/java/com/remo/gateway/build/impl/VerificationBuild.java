package com.remo.gateway.build.impl;

import com.remo.gateway.build.GatewayBuild;
import com.remo.gateway.mapper.BlacklistMapper;
import com.remo.gateway.mapper.entity.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("verificationBuild")
public class VerificationBuild implements GatewayBuild {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Boolean blackBlock(String ipAddress) {
        // 1.查询数据库黑名单
        Blacklist blacklist = blacklistMapper.findBlacklist(ipAddress);
        return blacklist != null;
    }
}
