package com.remo.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "remo")
public class RemoProperties {

    private SecurityProperties security = new SecurityProperties();

    private boolean openAopLog = true;

    private SwaggerProperties swagger = new SwaggerProperties();
}
