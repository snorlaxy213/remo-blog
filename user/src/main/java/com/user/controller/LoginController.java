package com.user.controller;

import com.user.constant.ViewConstants;
import com.user.pojo.dto.UserDto;
import com.user.pojo.vo.LoginVo;
import com.user.pojo.vo.ResponseVo;
import com.user.service.UserService;
import com.user.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "System security")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 登录后执行的操作
     * @param loginVo
     * @param bindingResult
     * @return responseVo
     */
    @ApiOperation(value = "login", notes = "check if userName and password are correct")
    @PostMapping("login")
    public ResponseVo login(@Validated @RequestBody LoginVo loginVo, BindingResult bindingResult) {
        ResponseVo responseVo;
        if (bindingResult.hasErrors()) {
            responseVo = ResponseUtil.initErrorResultVO(bindingResult, ViewConstants.WRONG_INPUT);
        } else {
            UserDto userDto = userService.login(loginVo);
            responseVo = ResponseUtil.initSuccessResultVO(userDto);
        }
        return responseVo;
    }
}
