package com.remo.user.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("remoProperties")
@Configuration
@ConfigurationProperties(prefix = "remo")
public class RemoProperties {

    private boolean openAopLog = true;

    private SecurityProperties security = new SecurityProperties();

    public boolean isOpenAopLog() {
        return openAopLog;
    }

    public void setOpenAopLog(boolean openAopLog) {
        this.openAopLog = openAopLog;
    }

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }
}
