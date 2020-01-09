package com.remo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.remo.mapper")
@EnableSwagger2
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
//        SpringApplication.run(UserApplication.class, args);
    }
}
