package com.remo.repository;

import com.remo.pojo.po.SeckillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("seckillGoodsRepository")
public interface SeckillGoodsRepository extends JpaRepository<SeckillGoods, Long> {
}
