package com.remo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.remo.mapper")
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
//        SpringApplication.run(UserApplication.class, args);
    }
}
