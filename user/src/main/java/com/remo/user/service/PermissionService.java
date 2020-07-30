package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.user.pojo.dto.PermissionDto;
import com.remo.user.pojo.po.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<PermissionDto> listUserPermissions();

    List<PermissionDto> listUserPermissions(String username);
}
