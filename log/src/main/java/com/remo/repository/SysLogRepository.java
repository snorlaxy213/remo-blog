package com.remo.repository;

import com.remo.po.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogRepository  extends JpaRepository<SysLog,Integer> {

}