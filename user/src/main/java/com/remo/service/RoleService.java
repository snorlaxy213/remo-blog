package com.remo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.pojo.dto.RoleDto;
import com.remo.pojo.po.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<RoleDto> listUserRoles(String username);

    List<RoleDto> listUserRoles();

    Long addRole(RoleDto roleDto);

    Long updateUser(RoleDto roleDto);

}
