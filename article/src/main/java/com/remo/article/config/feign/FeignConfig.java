package com.remo.article.config.feign;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Feign Configuration
 *
 * @author Jules
 * @date 2019/11/15
 */
@Configuration
public class FeignConfig {

    public static int connectTimeOutMillis = 2000;//超时时间,单位ms
    public static int readTimeOutMillis = 3000;//获取资源时间

    /**
     * FeignClient的默认超时时间为10s
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    /**
     * FeignClient的默认不会开启重试机制,默认为5次，需要自定义配置。
     *
     * 开启重试机制,重试问隔为 100 毫秒,最大重试时间为1秒,自定义重试次数为3次
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), 3);
    }
}
