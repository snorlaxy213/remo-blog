package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.user.pojo.dto.RoleDto;
import com.remo.user.pojo.po.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    RoleDto findUserRoleById(Long id);

    List<RoleDto> listUserRoles(String username);

    List<RoleDto> listUserRoles();

    Long addRole(RoleDto roleDto);

    Long updateRole(RoleDto roleDto);

}
