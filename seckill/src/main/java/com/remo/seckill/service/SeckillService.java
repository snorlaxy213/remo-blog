package com.remo.seckill.service;

import com.remo.seckill.pojo.dto.OrderDto;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;

import java.awt.image.BufferedImage;

public interface SeckillService {

    BufferedImage createVerifyCode(Long userId, Long goodsId);

    boolean checkPath(Long userId, Long goodsId, String path);

    boolean checkVerifyCode(Long userId, Long goodsId, Integer verifyCode);

    String createSeckillPath(Long userId, Long goodsId);

    OrderDto seckill(Long userId, SeckillGoodsDto seckillGoodsDto);

    long getSeckillResult(Long userId, long goodsId);
}
