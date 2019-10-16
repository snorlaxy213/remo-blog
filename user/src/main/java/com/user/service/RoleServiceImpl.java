package com.user.service;

import com.user.mapper.RoleMapper;
import com.user.pojo.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> listUserRoles(String account) {
        return roleMapper.listUserRoles(account);
    }
}
