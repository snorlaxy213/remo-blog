package com.remo.file;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FileApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
