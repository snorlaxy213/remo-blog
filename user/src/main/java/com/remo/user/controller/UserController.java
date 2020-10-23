package com.remo.user.controller;

import com.remo.user.pojo.dto.UserDto;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.UserService;
import com.remo.user.utils.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    UserService userService;

    @GetMapping("/users")
    public ResponseVo listUsers() {
        return ResponseUtil.initSuccessResultVO(userService.listUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(id));
    }

    @PostMapping("/user")
    public ResponseVo addUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.addUser(userDto));
    }

    @PutMapping("/user")
    public ResponseVo updateUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.updateUser(userDto));
    }

}
