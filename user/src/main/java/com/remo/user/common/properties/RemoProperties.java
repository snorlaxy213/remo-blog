package com.remo.user.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component("remoProperties")
@Configuration
@ConfigurationProperties(prefix = "remo")
public class RemoProperties {

    private boolean openAopLog = true;

    private SecurityProperties security = new SecurityProperties();

    private SwaggerProperties swagger = new SwaggerProperties();
}
