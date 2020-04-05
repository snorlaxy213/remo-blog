package com.remo.controller;

import com.remo.pojo.dto.UserDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.UserService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户登录以及用户管理
 */
@RestController
@Api(tags = "User")
public class UserController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @ApiOperation(value = "findAll", notes = "findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/user/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo findAll() {
        return ResponseUtil.initSuccessResultVO(userService.findAll());
    }


    @ApiOperation(value = "findById", notes = "findById")
    @GetMapping("/user/findById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo findById(@PathVariable("id") String id) {
        return ResponseUtil.initSuccessResultVO(userService.findById(Long.valueOf(id)));
    }

    @ApiOperation(value = "add", notes = "add")
    @PostMapping("/user/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo addUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.addUser(userDto));
    }

    @ApiOperation(value = "update", notes = "update")
    @PostMapping("/user/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo updateUser(@RequestBody UserDto userDto) {
        return ResponseUtil.initSuccessResultVO(userService.updateUser(userDto));
    }

}
