package com.remo.seckill.service.impl;

import com.remo.seckill.common.PrefixConstant;
import com.remo.seckill.pojo.dto.OrderDto;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.dto.SeckillOrderDto;
import com.remo.seckill.service.GoodsService;
import com.remo.seckill.service.OrderService;
import com.remo.seckill.service.SeckillOrderService;
import com.remo.seckill.service.SeckillService;
import com.remo.seckill.utils.CommonUtil;
import com.remo.seckill.utils.MD5Util;
import com.remo.seckill.utils.RedisUtil;
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

    @Resource(name = "seckillOrderServiceImpl")
    SeckillOrderService seckillOrderServiceImpl;

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
        }
        else {
            //如果库存不存在则内存标记为true
            setGoodsOver(seckillGoodsDto.getGoodsId());
            //返回空OrderDto对象
            return new OrderDto();
        }
    }

    @Override
    public long getSeckillResult(Long userId, long goodsId) {
        SeckillOrderDto seckillOrderDto = seckillOrderServiceImpl.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
        if (seckillOrderDto != null) {//秒杀成功
            return seckillOrderDto.getOrderId();
        }
        else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                //秒杀失败
                return -1;
            }
            else {
                //排队中
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisUtil.set(PrefixConstant.SECKILL_GOODS_OVER_PREFIX + goodsId, true);
    }

    private boolean getGoodsOver(Long goodsId) {
        return (Boolean) redisUtil.get(PrefixConstant.SECKILL_GOODS_OVER_PREFIX + goodsId) == true;
    }
}
