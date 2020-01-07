package com.remo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.mapper.RoleMapper;
import com.remo.pojo.dto.RoleDto;
import com.remo.pojo.po.Role;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private Mapper dozer;

    @Override
    public List<RoleDto> listUserRoles(String username) {
        List<Role> roles = roleMapper.listUserRoles(username);
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(role -> roleDtos.add(dozer.map(role, RoleDto.class)));
        return roleDtos;
    }
}