package com.remo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Converter;
import com.remo.pojo.po.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 2561862214725663483L;
    private Long userId;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String birthday;
    private String brief;
    private String avatar;
    private Integer version;

    public User convertToUser() {
        UserDtoConvert userDtoConvert = new UserDtoConvert();
        User convert = userDtoConvert.convert(this);
        return convert;
    }

    public UserDto convertFor(User user) {
        UserDtoConvert userDtoConvert = new UserDtoConvert();
        UserDto convert = userDtoConvert.reverse().convert(user);
        return convert;
    }

    private static class UserDtoConvert extends Converter<UserDto, User> {
        @Override
        protected User doForward(UserDto userDto) {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            return user;
        }

        @Override
        protected UserDto doBackward(User user) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }
    }

}
