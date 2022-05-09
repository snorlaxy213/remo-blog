package com.remo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.user.pojo.po.Permission;

import java.util.List;

public interface PermissionDao extends BaseMapper<Permission> {

    List<Permission> findUserPermissions(String userName);
}