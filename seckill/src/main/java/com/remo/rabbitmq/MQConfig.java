package com.remo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String DIRECT_EXCHANGE = "direct";
    public static final String FANOUT_EXCHANGE = "fanout";
    public static final String TOPIC_EXCHANGE = "topic";

    public static final String SECKILL_QUEUE = "seckill";

    @Bean
    public Queue queue() {
        return new Queue("seckill");
    }

}
