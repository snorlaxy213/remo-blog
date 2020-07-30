package com.remo.seckill.controller;

import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.vo.ResponseVo;
import com.remo.seckill.service.GoodsService;
import com.remo.seckill.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GoodsController {

    @Resource(name = "goodsServiceImpl")
    GoodsService goodsServiceImpl;

    @RequestMapping(value = "seckillGoods", method = RequestMethod.GET)
    public ResponseVo listSeckillGoods() {
        return ResponseUtil.initSuccessResultVO(goodsServiceImpl.listSeckillGoods());
    }

    @RequestMapping(value = "/seckillGoods/{goodsId}", method = RequestMethod.GET)
    public ResponseVo getSeckillGoods(@PathVariable("goodsId") long goodsId) {
        SeckillGoodsDto seckillGoodsDto = goodsServiceImpl.getSeckillGoodsById(goodsId);
        return ResponseUtil.initSuccessResultVO(seckillGoodsDto);
    }
}
