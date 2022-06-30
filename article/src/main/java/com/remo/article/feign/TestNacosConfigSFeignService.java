package com.remo.article.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user") // 指向服务提供者应用
public interface TestNacosConfigSFeignService {

    @GetMapping("/config/echo/{message}")
    String echo(@PathVariable("message") String message);
}
