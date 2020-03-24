package com.remo.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
@Slf4j
public class RabbitMQConsumer {

    @Resource(name = "goodsServiceImpl")
    GoodsService goodsServiceImpl;

    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)

    public void receiver(String message) {
        log.info("receive message:" + message);
        ObjectMapper mapper = new ObjectMapper();
        SeckillMessage seckillMessage;
        try {
            seckillMessage = mapper.readValue(message, SeckillMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            seckillMessage = new SeckillMessage();
        }

        GoodsVo goods = goodsServiceImpl.getGoodsVoByGoodsId(seckillMessage.getGoodsId());
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.valueOf(user.getNickname()), goodsId);
        if (order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }
}
