package com.remo.userShiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.userShiro.pojo.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user")
    List<User> findAll();

    @Select("select * from sys_user user where user.username = #{username}")
    User findByUsername(String username);

    @Select("select * from sys_user user where user.user_id = #{id}")
    User findById(Long id);

    @Select("select role_id from sys_user_role user_role where user_role.user_id = #{id}")
    List<Long> findRoleById(Long id);
}
