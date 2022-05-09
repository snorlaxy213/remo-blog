package com.remo.user.common.enums;

public enum LoginType {

    /**
     * over limit.
     */
    PASSWORD_LOGIN("密码登录", 1);

    public final String description;

    public final Integer loginType;

    LoginType(String description, int loginType) {
        this.description = description;
        this.loginType = loginType;
    }
}
