package com.remo.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/config")
@RefreshScope
public class TestNacosConfigController {

    @Value("${user.nickname}")
    private String userName;

    @Value("${user.age}")
    private int userAge;

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @RequestMapping("/get")
    public void get() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @GetMapping("/echo/{message}")
    public String callEcho(@PathVariable String message) {
        return new String("[init] user name and userAge");
    }
}
