package com.remo.userShiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.userShiro.pojo.po.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listUserRoles(@Param("username") String username);

}
