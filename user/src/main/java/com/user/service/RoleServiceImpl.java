package com.user.service;

import com.user.mapper.RoleMapper;
import com.user.pojo.dto.RoleDto;
import com.user.pojo.dto.UserDto;
import com.user.pojo.po.Role;
import com.user.pojo.po.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    private Mapper dozer;

    @Override
    public List<RoleDto> listUserRoles(String username) {
        List<Role> roles = roleMapper.listUserRoles(username);
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(user -> roleDtos.add(dozer.map(user, RoleDto.class)));
        return roleDtos;
    }
}
