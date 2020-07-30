package com.remo.log.repository;

import com.remo.log.po.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sysLogRepository")
public interface SysLogRepository extends JpaRepository<SysLog, Integer> {

}
