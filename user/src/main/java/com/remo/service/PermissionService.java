package com.remo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.pojo.dto.PermissionDto;
import com.remo.pojo.po.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<PermissionDto> listUserPermissions();

    List<PermissionDto> listUserPermissions(String username);
}
