package com.remo.controller;

import com.remo.pojo.dto.UserDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.UserService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 */
@RestController("/user")
@Api(tags = "User")
public class UserController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @ApiOperation(value = "findAll", notes = "findAll")
    @GetMapping("/all")
    public ResponseVo findAll() {
        return ResponseUtil.initSuccessResultVO(userService.findAll());
    }


    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/{id}")
    public ResponseVo findById(@PathVariable Long id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(id));
    }

    @ApiOperation(value = "add", notes = "add")
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo addUser(@RequestBody UserDto userDto) {

        return null;
    }

}
