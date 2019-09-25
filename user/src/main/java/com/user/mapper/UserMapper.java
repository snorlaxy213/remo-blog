package com.user.mapper;

import com.user.pojo.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper{

    @Select("select * from sys_user")
    List<User> findAll();

    @Select("select * from sys_user user where user.account = #{account}")
    User findByAccount(String account);

    @Select("select * from sys_user user where user.user_id = #{id}")
    User findById(Long id);

    @Select("select role_id from sys_user_role user_role where user_role = #{id}")
    List<Long> findRoleById(Long id);
}
