package com.remo.service.impl;

import com.remo.common.PrefixConstant;
import com.remo.service.SeckillService;
import com.remo.utils.CommonUtil;
import com.remo.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.image.BufferedImage;

@Service("seckillServiceImpl")
public class SeckillServiceImpl implements SeckillService {

    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

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
        redisUtil.set(PrefixConstant.SECKILL_CACHE_PREFIX + userId, rnd);
        //输出图片
        return image;
    }
}
