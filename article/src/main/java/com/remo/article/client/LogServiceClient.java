package com.remo.article.client;

import com.remo.article.client.po.SysLog;
import com.remo.article.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "log", configuration = FeignConfig.class)
public interface LogServiceClient {

    @PostMapping(value = "/saveLog")
    void saveSysLog(@RequestBody SysLog sysLog);

}
