package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import po.SysLog;

@Repository
public interface SysLogRepository  extends JpaRepository<SysLog,Integer> {

}
