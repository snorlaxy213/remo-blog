package com.remo.service;

import com.remo.pojo.dto.SeckillOrderDto;

public interface SeckillOrderService {

    SeckillOrderDto getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId);

    long stockDesc(Long goodsId);
}
