package com.remo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import po.SysLog;
import service.LogService;

@Controller
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping(value = "/saveLog")
    public void saveSysLog(SysLog sysLog) {
        logService.saveSysLog(sysLog);
    }

}
