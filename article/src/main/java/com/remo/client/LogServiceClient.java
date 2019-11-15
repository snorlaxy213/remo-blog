package com.remo.client;

import com.remo.client.po.SysLog;
import com.remo.config.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "log", configuration = FeignConfig.class)
public interface LogServiceClient {

    void saveSysLog(SysLog sysLog);
}
