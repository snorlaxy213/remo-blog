package com.user.controller;

import com.user.pojo.dto.UserDto;
import com.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录以及用户管理
 */
@RestController
@Api(tags = "User")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/test/{id}")
    public UserDto findById(@PathVariable Long id) {
        UserDto userDto = new UserDto();
        return userDto;
    }
}
