package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.user.pojo.dto.UserDto;
import com.remo.user.pojo.po.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * fetch all data
     * -----------------------
     *
     * @return List<UserDto>
     */
    List<UserDto> listUsers();

    /**
     * fetch data by user id
     * -----------------------
     *
     * @param id id
     * @return UserDto
     */
    UserDto findById(Long id);

    /**
     * fetch data by username
     * -----------------------
     *
     * @param username username
     * @return UserDto
     */
    UserDto findByUsername(String username);

    /**
     * add user
     * -----------------------
     *
     * @param userDto userDto
     * @return id
     */
    Long addUser(UserDto userDto);

    /**
     * update user
     * -----------------------
     *
     * @param userDto userDto
     * @return id
     */
    Long updateUser(UserDto userDto);
}
