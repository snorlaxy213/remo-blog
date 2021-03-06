package com.remo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.user.pojo.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listUserRoles(@Param("username") String username);

}
