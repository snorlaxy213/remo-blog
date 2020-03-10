package com.remo.service;

import com.remo.pojo.dto.GoodsDto;
import com.remo.pojo.po.SeckillGoods;

import java.util.List;

public interface GoodsService {

    List<GoodsDto> listSeckillGoods();

    int addSeckillGoods(SeckillGoods seckillGoods);
}
