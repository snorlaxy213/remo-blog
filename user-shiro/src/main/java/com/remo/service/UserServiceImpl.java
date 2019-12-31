package com.remo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.mapper.UserMapper;
import com.remo.pojo.dto.UserDto;
import com.remo.pojo.po.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    Mapper dozer;

    @Override
    public List<UserDto> findAll() {
        List<User> users = userMapper.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(dozer.map(user, UserDto.class)));
        return userDtos;
    }

    @Override
    public UserDto findById(Long id) {
        return dozer.map(userMapper.findById(id), UserDto.class);
    }

    @Override
    public UserDto findByUsername(String username) {
        return dozer.map(userMapper.findByUsername(username), UserDto.class);
    }
}
