package com.remo.service;

import com.remo.pojo.dto.OrderDto;
import com.remo.pojo.dto.SeckillGoodsDto;

import java.awt.image.BufferedImage;

public interface SeckillService {

    BufferedImage createVerifyCode(Long userId, Long goodsId);

    boolean checkPath(Long userId, Long goodsId, String path);

    boolean checkVerifyCode(Long userId, Long goodsId, Integer verifyCode);

    String createSeckillPath(Long userId, Long goodsId);

    OrderDto seckill(Long userId, SeckillGoodsDto seckillGoodsDto);
}
