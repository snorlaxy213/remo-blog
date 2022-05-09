package com.remo.user.controller;

import com.remo.user.pojo.dto.query.LoginQuery;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.login.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public ResponseVo login(@RequestBody LoginQuery query) throws Exception {
        return LoginService.loginTypeMap.get(query.getLoginType()).login(query);
    }

}
