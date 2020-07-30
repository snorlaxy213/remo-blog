package com.remo.userShiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.userShiro.pojo.po.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findUserPermissions(String userName);
}