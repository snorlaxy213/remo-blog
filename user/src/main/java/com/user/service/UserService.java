package com.user.service;

import com.user.pojo.dto.UserDto;
import com.user.pojo.vo.LoginVo;

import java.util.List;

public interface UserService {

    /**
     * login
     * -----------------------
     * @param vo
     * @return UserDto
     */
    UserDto login(LoginVo vo);

    /**
     * fetch all data
     * -----------------------
     * @return List<UserDto>
     */
    List<UserDto> findAll();

    /**
     * fetch data by user id
     * -----------------------
     * @param id id
     * @return UserDto
     */
    UserDto findById(Long id);
}
