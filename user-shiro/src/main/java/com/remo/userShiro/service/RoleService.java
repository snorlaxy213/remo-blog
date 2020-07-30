package com.remo.userShiro.service;

import com.remo.userShiro.pojo.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> listUserRoles(String username);
}
