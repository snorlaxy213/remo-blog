package com.remo.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.remo.util.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 2055229953429884344L;

    // 唯一编号
    private String id = RandomStringUtils.randomNumeric(10);
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateUtil.formatFullTime(LocalDateTime.now(),DateUtil.FULL_TIME_SPLIT_PATTERN);
}
