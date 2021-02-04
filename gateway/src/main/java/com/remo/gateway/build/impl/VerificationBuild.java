package com.remo.gateway.build.impl;

import com.remo.gateway.build.GatewayBuild;
import com.remo.gateway.mapper.entity.Blacklist;
import com.remo.gateway.mapper.entity.BlacklistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VerificationBuild implements GatewayBuild {

    private static final Logger log = LoggerFactory.getLogger(VerificationBuild.class);

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Boolean blackBlock(String ipAddress) {
        // 2.查询数据库黑名单
        Blacklist blacklist = blacklistMapper.findBlacklist(ipAddress);
        return blacklist != null;
    }
}
