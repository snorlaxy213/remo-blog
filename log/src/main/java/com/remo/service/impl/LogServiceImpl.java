package com.remo.service.impl;

import com.remo.po.SysLog;
import com.remo.repository.SysLogRepository;
import com.remo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LogServiceImpl implements LogService {

    @Autowired
    SysLogRepository sysLogRepository;

    @Override
    @Transactional
    public void saveSysLog(SysLog sysLog) {
        sysLogRepository.saveAndFlush(sysLog);
    }

}
