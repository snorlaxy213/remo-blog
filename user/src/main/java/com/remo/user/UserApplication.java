package com.remo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 * @author Grio Vino
 */
@SpringBootApplication
@MapperScan("com.remo.user.dao")
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@ComponentScan
@ComponentScan(basePackages = "com.remo.auth.config")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
//        SpringApplication.run(UserApplication.class, args);
    }
}
