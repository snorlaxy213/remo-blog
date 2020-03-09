package com.remo.service.impl;

import com.remo.po.SysLog;
import com.remo.repository.SysLogRepository;
import com.remo.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("logServiceImpl")
public class LogServiceImpl implements LogService {

    @Resource(name = "sysLogRepository")
    SysLogRepository sysLogRepository;

    @Override
    @Transactional
    public void saveSysLog(SysLog sysLog) {

        sysLogRepository.save(sysLog);
    }

}
