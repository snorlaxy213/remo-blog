package com.remo.seckill.repository;

import com.remo.seckill.pojo.po.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
