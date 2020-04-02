package com.remo.service;

import com.remo.pojo.dto.SeckillGoodsDto;
import com.remo.pojo.po.SeckillGoods;

import java.util.List;

public interface GoodsService {

    List<SeckillGoodsDto> listSeckillGoods();

    int addSeckillGoods(SeckillGoods seckillGoods);

    boolean reduceStock(SeckillGoodsDto seckillGoodsDto);

    SeckillGoodsDto getSeckillGoodsById(Long goodsId);
}
