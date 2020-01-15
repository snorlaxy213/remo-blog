package com.remo.controller;

import com.remo.pojo.dto.UserDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.UserService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 */
@RestController("/user")
@Api(tags = "User")
public class UserController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/test/{id}")
    public ResponseVo findById(@PathVariable Long id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(id));
    }

    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long addUser(UserDto userDto) {
        return null;
    }

}
