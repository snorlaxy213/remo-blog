package com.remo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.user.pojo.po.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findUserPermissions(String userName);
}