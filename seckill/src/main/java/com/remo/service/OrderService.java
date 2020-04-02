package com.remo.service;

import com.remo.pojo.dto.OrderDto;
import com.remo.pojo.dto.SeckillGoodsDto;

public interface OrderService {

    OrderDto createOrder(Long userId, SeckillGoodsDto seckillGoodsDto);
}
