package com.remo.user.client;

import com.remo.user.client.hystrix.LogHystrix;
import com.remo.user.client.po.SysLog;
import com.remo.user.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "log", configuration = FeignConfig.class, fallback = LogHystrix.class)
public interface LogServiceClient {

    @PostMapping(value = "/saveLog")
    void saveSysLog(@RequestBody SysLog sysLog);

}
