package com.remo.service.impl;

import com.remo.pojo.dto.GoodsDto;
import com.remo.pojo.po.Goods;
import com.remo.pojo.po.SeckillGoods;
import com.remo.repository.GoodsRepository;
import com.remo.repository.SeckillGoodsRepository;
import com.remo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("goodsServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    @Resource(name = "goodsRepository")
    GoodsRepository goodsRepository;

    @Resource(name = "seckillGoodsRepository")
    SeckillGoodsRepository seckillGoodsRepository;

    @Override
    public List<GoodsDto> listSeckillGoods() {
        List<SeckillGoods> seckillGoods = seckillGoodsRepository.findAll();
        List<Long> ids = seckillGoods.stream().map(SeckillGoods::getGoodsId).collect(Collectors.toList());
        List<Goods> goods = goodsRepository.findByGoodsIdIn(ids);
        List<GoodsDto> goodsDtos = new ArrayList<>();
        goods.forEach(goodsTemp -> {
            GoodsDto goodsDto = new GoodsDto();
            BeanUtils.copyProperties(goodsTemp, goodsDto);
            goodsDtos.add(goodsDto);
        });

        return goodsDtos;
    }

    @Override
    public int addSeckillGoods(SeckillGoods seckillGoods) {
        return 0;
    }
}
