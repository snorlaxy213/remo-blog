package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.dto.UserDto;
import com.user.pojo.po.User;

import java.util.List;

public interface UserService extends IService<User> {

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

    UserDto findByUsername(String username);
}
