package com.remo.controller;

import com.remo.pojo.dto.SeckillOrderDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.rabbitmq.RabbitMQProvider;
import com.remo.service.SeckillOrderService;
import com.remo.service.SeckillService;
import com.remo.utils.ImageUtil;
import com.remo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
public class SeckillController {

    @Resource(name = "seckillServiceImpl")
    SeckillService seckillServiceImpl;

    @Resource(name = "seckillOrderServiceImpl")
    SeckillOrderService seckillOrderServiceImpl;

    @Resource(name = "rabbitMQProvider")
    RabbitMQProvider rabbitMQProvider;

    private final HashMap<Long, Boolean> localOverMap = new HashMap<>();

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
    public ResponseVo seckill(@PathVariable("path") String path,
                              @RequestParam("userId") Long userId,
                              @RequestParam("GoodsId") Long goodsId) {
        //验证path
        boolean pathCheck = seckillServiceImpl.checkPath(userId, goodsId, path);
        if (!pathCheck) {
            return ResponseUtil.initErrorResultVO("验证错误");
        }

        //是否已经秒杀到
        SeckillOrderDto seckillOrderDto = seckillOrderServiceImpl.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
        if (seckillOrderDto != null) {
            return ResponseUtil.initErrorResultVO("请勿重复购买");
        }
        //内存标记，减少redis访问
        boolean over = localOverMap.get(goodsId);
        if (over) {
            return ResponseUtil.initErrorResultVO("物品已下架");
        }
        //预见库存
        Long stock = seckillOrderServiceImpl.stockDesc(goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return ResponseUtil.initErrorResultVO("物品已下架");
        }

        rabbitMQProvider.sendSeckillMessage(userId, goodsId);
        return ResponseUtil.initSuccessResultVO("恭喜秒杀成功");
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ResponseVo seckillResult(@RequestParam("userId") Long userId,
                                    @RequestParam("GoodsId") Long goodsId) {
        return ResponseUtil.initSuccessResultVO(seckillServiceImpl.getSeckillResult(userId, goodsId));
    }
}
