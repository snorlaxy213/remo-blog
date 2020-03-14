package com.remo.repository;

import com.remo.pojo.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsRepository")
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findByGoodsIdIn(List<Long> ids);
}
