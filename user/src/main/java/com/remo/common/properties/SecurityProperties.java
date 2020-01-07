package com.remo.common.properties;

public class SecurityProperties {

    /**
     * token默认有效时间 1天
     */
    private Long jwtTimeOut = 86400L;

    public Long getJwtTimeOut() {
        return jwtTimeOut;
    }

    public void setJwtTimeOut(Long jwtTimeOut) {
        this.jwtTimeOut = jwtTimeOut;
    }
}
