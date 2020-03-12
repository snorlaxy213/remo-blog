package com.remo.service;

import java.awt.image.BufferedImage;

public interface SeckillService {

    BufferedImage createVerifyCode(Long userId, Long goodsId);
}
