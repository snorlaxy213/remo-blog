package com.remo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${foo}")
    String foo;

    @GetMapping(value = "foo")
    public String testFoo() {
        return foo;
    }
}
