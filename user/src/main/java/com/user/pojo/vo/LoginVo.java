package com.user.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Login parameter
 *
 * @author Jules
 * @date 2019/6/25
 */
@Data
public class LoginVo {
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
