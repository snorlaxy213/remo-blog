package com.remo.log.controller;

import com.remo.log.po.SysLog;
import com.remo.log.service.LogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LogController {

    @Resource(name = "logServiceImpl")
    LogService logService;

    @PostMapping(value = "/saveLog")
    public void saveSysLog(@RequestBody SysLog sysLog) {
        logService.saveSysLog(sysLog);
    }

}
