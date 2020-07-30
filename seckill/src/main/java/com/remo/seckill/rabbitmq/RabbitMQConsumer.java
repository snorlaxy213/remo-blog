package com.remo.seckill.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.dto.SeckillOrderDto;
import com.remo.seckill.service.GoodsService;
import com.remo.seckill.service.SeckillOrderService;
import com.remo.seckill.service.SeckillService;
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

    @Resource(name = "seckillServiceImpl")
    SeckillService seckillServiceImpl;

    @Resource(name = "seckillOrderServiceImpl")
    SeckillOrderService seckillOrderServiceImpl;

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
        Long userId = seckillMessage.getUserId();
        Long goodsId = seckillMessage.getGoodsId();

        SeckillGoodsDto seckillGoodsDto = goodsServiceImpl.getSeckillGoodsById(goodsId);
        int stock = seckillGoodsDto.getStockCount();
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        SeckillOrderDto seckillOrderDto = seckillOrderServiceImpl.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
        if (seckillOrderDto != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        seckillServiceImpl.seckill(userId, seckillGoodsDto);
    }
}
