package com.remo.user.service.login;

import com.remo.user.pojo.dto.query.LoginQuery;
import com.remo.user.pojo.vo.ResponseVo;

import java.util.HashMap;
import java.util.Map;

public interface LoginService {

    Map<Integer, LoginService> loginTypeMap = new HashMap<>();

    ResponseVo login(LoginQuery query) throws Exception;
}
