package com.remo.client;

import com.remo.client.hystrix.LogHystrix;
import com.remo.client.po.SysLog;
import com.remo.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "log",
        configuration = FeignConfig.class, fallback = LogHystrix.class)
public interface LogServiceClient {

    @PostMapping(value = "/saveLog")
    void saveSysLog(@RequestBody SysLog sysLog);

}
