package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.dto.PermissionDto;
import com.user.pojo.po.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<PermissionDto> findUserPermissions(String username);
}
