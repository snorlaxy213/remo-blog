package com.remo.service.impl;

import com.remo.pojo.dto.GoodsDto;
import com.remo.pojo.po.SeckillGoods;
import com.remo.repository.GoodsRepository;
import com.remo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("goodsServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    @Resource(name = "goodsRepository")
    GoodsRepository goodsRepository;

    @Override
    public List<GoodsDto> listSeckillGoods() {

        return null;
    }

    @Override
    public int addSeckillGoods(SeckillGoods seckillGoods) {
        return 0;
    }
}
