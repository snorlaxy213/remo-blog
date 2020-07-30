package com.remo.user.client.hystrix;

import com.remo.user.client.LogServiceClient;
import com.remo.user.client.po.SysLog;
import org.springframework.stereotype.Component;

@Component
public class LogHystrix implements LogServiceClient {

    @Override
    public void saveSysLog(SysLog sysLog) {
        System.out.println("Log is not working");
    }
}
