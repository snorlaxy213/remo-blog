package com.remo.seckill.service;

import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.po.SeckillGoods;

import java.util.List;

public interface GoodsService {

    List<SeckillGoodsDto> listSeckillGoods();

    int addSeckillGoods(SeckillGoods seckillGoods);

    boolean reduceStock(SeckillGoodsDto seckillGoodsDto);

    SeckillGoodsDto getSeckillGoodsById(Long goodsId);
}
