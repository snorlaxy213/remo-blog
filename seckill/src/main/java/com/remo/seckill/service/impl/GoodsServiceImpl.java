package com.remo.seckill.service.impl;

import com.remo.seckill.pojo.dto.GoodsDto;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.po.Goods;
import com.remo.seckill.pojo.po.SeckillGoods;
import com.remo.seckill.repository.GoodsRepository;
import com.remo.seckill.repository.SeckillGoodsRepository;
import com.remo.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("goodsServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    @Resource(name = "goodsRepository")
    GoodsRepository goodsRepository;

    @Resource(name = "seckillGoodsRepository")
    SeckillGoodsRepository seckillGoodsRepository;

    @Override
    public List<SeckillGoodsDto> listSeckillGoods() {
        List<SeckillGoods> seckillGoods = seckillGoodsRepository.findAll();
        List<SeckillGoodsDto> seckillGoodsDtos = new ArrayList<>();
        seckillGoods.forEach(seckillGoodsTemp -> {
            Goods goods = goodsRepository.findById(seckillGoodsTemp.getGoodsId()).get();
            GoodsDto goodsDto = new GoodsDto();
            BeanUtils.copyProperties(goods, goodsDto);

            SeckillGoodsDto seckillGoodsDto = new SeckillGoodsDto();
            BeanUtils.copyProperties(seckillGoodsTemp, seckillGoodsDto);
            seckillGoodsDto.setGoodsDto(goodsDto);

            seckillGoodsDtos.add(seckillGoodsDto);
        });

        return seckillGoodsDtos;
    }

    @Override
    public SeckillGoodsDto getSeckillGoodsById(Long goodsId) {
        Optional<SeckillGoods> goodsOptional = seckillGoodsRepository.findByGoodsId(goodsId);
        SeckillGoodsDto seckillGoodsDto = new SeckillGoodsDto();
        goodsOptional.ifPresent(seckillGoods -> BeanUtils.copyProperties(seckillGoods, seckillGoodsDto));
        return seckillGoodsDto;
    }

    @Override
    public boolean reduceStock(SeckillGoodsDto seckillGoodsDto) {
        Optional<SeckillGoods> seckillGoodsOptional = seckillGoodsRepository.findByGoodsId(seckillGoodsDto.getGoodsId());

        Integer stockCount = 0;
        if (seckillGoodsOptional.isPresent()) {
            SeckillGoods seckillGoods = seckillGoodsOptional.get();
            stockCount = seckillGoods.getStockCount();

            if (stockCount > 0) {
                stockCount = stockCount - 1;
                seckillGoods.setStockCount(stockCount);
                seckillGoodsRepository.save(seckillGoods);
            }
        }

        return stockCount > 0;
    }

    @Override
    public int addSeckillGoods(SeckillGoods seckillGoods) {
        return 0;
    }
}
