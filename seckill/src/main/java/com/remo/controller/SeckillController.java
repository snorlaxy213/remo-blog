package com.remo.controller;

import com.remo.pojo.vo.ResponseVo;
import com.remo.service.SeckillService;
import com.remo.utils.ImageUtil;
import com.remo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@RestController
public class SeckillController {

    @Resource(name = "seckillServiceImpl")
    SeckillService seckillServiceImpl;

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public ResponseVo getSeckillPath(@RequestParam("userId") Long userId,
                                     @RequestParam("goodsId") Long goodsId,
                                     @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode) {
        boolean verifyCodeCheck = seckillServiceImpl.checkVerifyCode(userId, goodsId, verifyCode);
        if (!verifyCodeCheck) {
            return ResponseUtil.initErrorResultVO("验证错误");
        }
        return ResponseUtil.initSuccessResultVO(seckillServiceImpl.createSeckillPath(userId, goodsId));
    }

    @GetMapping(value = "/verifyCode", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getSeckillVerifyCod(@RequestParam("userId") Long userId, @RequestParam("goodsId") Long goodsId) throws IOException {
        BufferedImage image = seckillServiceImpl.createVerifyCode(userId, goodsId);
        return ImageUtil.imageToBytes(image);
    }

    @RequestMapping(value = "/{path}/seckill", method = RequestMethod.POST)
    public ResponseVo miaosha(@PathVariable("path") String path,
                              @RequestParam("userId") Long userId,
                              @RequestParam("goodsId") long goodsId) {
        //验证path
        boolean pathCheck = seckillServiceImpl.checkPath(userId, goodsId, path);
        if (!pathCheck) {
            return ResponseUtil.initErrorResultVO("验证错误");
        }

        //是否已经秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.valueOf(user.getNickname()), goodsId);
        if (order != null) {
            result.withError(REPEATE_MIAOSHA.getCode(), REPEATE_MIAOSHA.getMessage());
            return result;
        }
        //内存标记，减少redis访问
        boolean over = localOverMap.get(goodsId);
        if (over) {
            result.withError(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
            return result;
        }
        //预见库存
        Long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, "" + goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            result.withError(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
            return result;
        }
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setGoodsId(goodsId);
        mm.setUser(user);
        mqSender.sendMiaoshaMessage(mm);
        return result;
    }
}
