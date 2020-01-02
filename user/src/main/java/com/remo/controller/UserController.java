package com.remo.controller;

import com.remo.pojo.dto.UserDto;
import com.remo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/user/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long addUser(UserDto userDto) {
        return null;
    }

}
