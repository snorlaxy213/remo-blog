package com.user.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User业务逻辑实体类
 *
 * @author Jules
 * @date 2019/5/27
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 2561862214725663483L;
    private Long userId;
    private String account;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String birthday;
    private String brief;
    private String avatar;
}
