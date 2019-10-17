package com.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.constant.BusinessConstant;
import com.user.constant.ViewConstants;
import com.user.exception.exception.BusinessException;
import com.user.mapper.UserMapper;
import com.user.pojo.dto.UserDto;
import com.user.pojo.po.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
        return dozer.map(userMapper.findByAccount(username), UserDto.class);
    }
}
