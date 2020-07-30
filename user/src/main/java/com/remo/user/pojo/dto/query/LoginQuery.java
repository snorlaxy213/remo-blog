package com.remo.user.pojo.dto.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginQuery {
    private String username;
    private String password;
}
