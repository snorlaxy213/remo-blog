package com.remo.service;

import com.remo.pojo.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> listUserRoles(String username);
}
