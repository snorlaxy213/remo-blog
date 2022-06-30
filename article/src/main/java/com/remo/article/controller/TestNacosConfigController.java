package com.remo.article.controller;

import com.remo.article.feign.TestNacosConfigSFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/config")
@RefreshScope
public class TestNacosConfigController {

    @Value("${user.nickname}")
    private String userName;

    @Value("${user.age}")
    private int userAge;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestNacosConfigSFeignService testNacosConfigSFeignService;

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @RequestMapping(method = RequestMethod.GET,name = "/get")
    public void get() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @GetMapping("/call/echo/{message}")
    public String callEcho(@PathVariable String message) {
        return restTemplate.getForObject("http://user/config/echo/" + message, String.class);
    }

    @GetMapping("/feign/echo/{message}")
    public String feignEcho(@PathVariable String message) {
        return testNacosConfigSFeignService.echo(message);
    }
}
