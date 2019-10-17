package com.user.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.validation.groups.Login;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 2561862214725663483L;
    private Long userId;
    @NotBlank(message = "用户名不能为空", groups = Login.class)
    private String username;
    private String name;
    @NotBlank(message = "密码不能为空", groups = Login.class)
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String birthday;
    private String brief;
    private String avatar;
    private Integer version;
}
