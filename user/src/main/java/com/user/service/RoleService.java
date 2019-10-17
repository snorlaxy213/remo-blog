package com.user.service;

import com.user.pojo.dto.RoleDto;
import com.user.pojo.po.Role;

import java.util.List;

public interface RoleService {

    List<RoleDto> listUserRoles(String username);
}
