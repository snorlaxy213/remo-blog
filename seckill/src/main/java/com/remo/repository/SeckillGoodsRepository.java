package com.remo.repository;

import com.remo.pojo.po.SeckillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("seckillGoodsRepository")
public interface SeckillGoodsRepository extends JpaRepository<SeckillGoods, Long> {

    Optional<SeckillGoods> findByGoodsId(Long goodsId);
}
