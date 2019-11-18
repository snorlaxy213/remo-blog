package com.remo.controller;

import com.remo.po.SysLog;
import com.remo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping(value = "/saveLog")
    public void saveSysLog(@RequestBody SysLog sysLog) {
        logService.saveSysLog(sysLog);
    }

}
