package com.remo.userShiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.userShiro.pojo.dto.PermissionDto;
import com.remo.userShiro.pojo.po.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<PermissionDto> findUserPermissions(String username);
}
