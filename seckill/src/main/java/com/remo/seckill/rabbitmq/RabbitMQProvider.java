package com.remo.seckill.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rabbitMQProvider")
public class RabbitMQProvider {

    @Autowired
    private AmqpTemplate template;

    public void sendSeckillMessage(Long userId, Long goodsId) {
        SeckillMessage message = new SeckillMessage();
        message.setUserId(userId).setGoodsId(goodsId);
        ObjectMapper mapper = new ObjectMapper();
        String messageStr;
        try {
            messageStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            messageStr = "";
        }
        template.convertAndSend(MQConfig.SECKILL_QUEUE, messageStr);
    }

}
