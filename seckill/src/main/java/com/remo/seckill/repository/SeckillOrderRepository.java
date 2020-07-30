package com.remo.seckill.repository;

import com.remo.seckill.pojo.po.SeckillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("seckillOrderRepository")
public interface SeckillOrderRepository extends JpaRepository<SeckillOrder, Integer> {
}
