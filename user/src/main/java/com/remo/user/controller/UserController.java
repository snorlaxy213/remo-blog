package com.remo.user.controller;

import com.remo.user.pojo.dto.UserDto;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.UserService;
import com.remo.user.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 */
@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserController {

    @Resource(name = "userService")
    UserService userService;

    @ApiOperation(value = "listUsers", notes = "listUsers")
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo listUsers() {
        return ResponseUtil.initSuccessResultVO(userService.listUsers());
    }


    @ApiOperation(value = "find user by id", notes = "find user by id")
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(id));
    }

    @ApiOperation(value = "add user", notes = "add user")
    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo addUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.addUser(userDto));
    }

    @ApiOperation(value = "update user", notes = "update user")
    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo updateUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.updateUser(userDto));
    }

}
