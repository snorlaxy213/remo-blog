package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import po.SysLog;
import repository.SysLogRepository;
import service.LogService;

public class LogServiceImpl implements LogService {

    @Autowired
    SysLogRepository sysLogRepository;

    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLogRepository.saveAndFlush(sysLog);
    }

}
