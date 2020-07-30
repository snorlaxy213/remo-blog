package com.remo.seckill.service;

import com.remo.seckill.pojo.dto.SeckillOrderDto;

public interface SeckillOrderService {

    SeckillOrderDto getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId);

    long stockDesc(Long goodsId);
}
