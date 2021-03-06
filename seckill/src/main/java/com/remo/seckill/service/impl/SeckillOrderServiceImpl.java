package com.remo.seckill.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.seckill.common.PrefixConstant;
import com.remo.seckill.pojo.dto.SeckillOrderDto;
import com.remo.seckill.service.SeckillOrderService;
import com.remo.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("seckillOrderServiceImpl")
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

    @Autowired
    ObjectMapper mapper;

    @Override
    public SeckillOrderDto getSeckillOrderByUserIdAndGoodsId(long userId, long goodsId) {
        return mapper.convertValue(redisUtil.get(PrefixConstant.SECKILL_SECKILL_ORDER_PREFIX + userId + goodsId), SeckillOrderDto.class);
    }

    @Override
    public long stockDesc(Long goodsId) {
        return redisUtil.decr(String.valueOf(goodsId), 1);
    }
}
