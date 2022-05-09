package com.remo.user.controller;

import com.remo.user.pojo.dto.UserDto;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.UserService;
import com.remo.user.utils.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 * @author Grio Vino
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    UserService userService;

    @GetMapping
    public ResponseVo listUsers() {
        return ResponseUtil.initSuccessResultVO(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(id));
    }

    @PostMapping
    public ResponseVo addUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.addUser(userDto));
    }

    @PutMapping
    public ResponseVo updateUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.updateUser(userDto));
    }

}
