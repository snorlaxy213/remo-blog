package com.remo.gateway.build.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

    private static final Set<String> sensitiveWords = new HashSet<>(10);
    static {
        sensitiveWords.add("习");
        sensitiveWords.add("日");
    };

    @Override
    public Boolean toVerifyMap(String ipAddres, Map<String, List<String>> attributes) {
        // 4.外网传递参数验证
        Set<Entry<String, List<String>>> entrySet = attributes.entrySet();
        for(Entry<String, List<String>> entry : entrySet){
            // String key = entry.getKey().toString();
            List<String> values = entry.getValue();
            for(String value : values){
                if(sensitiveWords.contains(value)){
                    value = "*";
                }
            }
        }
		return true;
    }

    
}
