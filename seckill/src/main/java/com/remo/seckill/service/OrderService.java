package com.remo.seckill.service;

import com.remo.seckill.pojo.dto.OrderDto;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;

public interface OrderService {

    OrderDto createOrder(Long userId, SeckillGoodsDto seckillGoodsDto);
}
