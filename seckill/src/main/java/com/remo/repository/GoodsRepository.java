package com.remo.repository;

import com.remo.pojo.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("goodsLogRepository")
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

}
