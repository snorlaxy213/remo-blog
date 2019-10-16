package com.user.service;

import com.user.pojo.po.Role;

import java.util.List;

public interface RoleService {

    List<Role> listUserRoles(String account);
}
