package com.remo.service.impl;

import com.remo.common.PrefixConstant;
import com.remo.pojo.dto.OrderDto;
import com.remo.pojo.dto.SeckillGoodsDto;
import com.remo.service.GoodsService;
import com.remo.service.OrderService;
import com.remo.service.SeckillService;
import com.remo.utils.CommonUtil;
import com.remo.utils.MD5Util;
import com.remo.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.image.BufferedImage;
import java.util.UUID;

@Service("seckillServiceImpl")
public class SeckillServiceImpl implements SeckillService {

    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

    @Resource(name = "goodsServiceImpl")
    GoodsService goodsServiceImpl;

    @Resource(name = "orderServiceImpl")
    OrderService orderServiceImpl;

    /**
     * 利用ScriptEngineManager对计算公式的支持
     *
     * @param exp
     * @return 计算结果
     */
    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Integer catch1 = (Integer) engine.eval(exp);
            return catch1.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public BufferedImage createVerifyCode(Long userId, Long goodsId) {
        //生成随机验证码
        String verifyCode = CommonUtil.generateVerifyCode();
        //生成图片
        BufferedImage image = CommonUtil.generateVerifyCodeImage(verifyCode);
        //计算验证码结果
        int rnd = calc(verifyCode);
        //把验证码存到redis中
        redisUtil.set(PrefixConstant.SECKILL_VERIFY_CODE_PREFIX + userId, rnd);
        //输出图片
        return image;
    }

    @Override
    public boolean checkVerifyCode(Long userId, Long goodsId, Integer verifyCode) {
        String verifyCodeKey = PrefixConstant.SECKILL_VERIFY_CODE_PREFIX + userId;
        Integer codeOld = (int) redisUtil.get(verifyCodeKey);
        if (codeOld == null || codeOld - verifyCode != 0) {
            return false;
        }
        redisUtil.del(verifyCodeKey);
        return true;
    }

    @Override
    public String createSeckillPath(Long userId, Long goodsId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String path = MD5Util.encrypt(uuid + goodsId);
        redisUtil.set(PrefixConstant.SECKILL_SECKILL_PATH_PREFIX + userId + "_" + goodsId, path);
        return path;
    }

    @Override
    public boolean checkPath(Long userId, Long goodsId, String path) {
        String oldPath = (String) redisUtil.get(PrefixConstant.SECKILL_SECKILL_PATH_PREFIX + userId + "_" + goodsId);
        return path.equals(oldPath);
    }

    @Override
    public OrderDto seckill(Long userId, SeckillGoodsDto seckillGoodsDto) {
        //减库存 下订单 写入秒杀订单
        boolean success = goodsServiceImpl.reduceStock(seckillGoodsDto);
        if (success) {
            return orderServiceImpl.createOrder(userId, seckillGoodsDto);
        } else {
            //如果库存不存在则内存标记为true
            setGoodsOver(seckillGoodsDto.getGoodsId());
            //返回空OrderDto对象
            return new OrderDto();
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisUtil.set(PrefixConstant.SECKILL_GOODS_OVER_PREFIX + goodsId, true);
    }
}
