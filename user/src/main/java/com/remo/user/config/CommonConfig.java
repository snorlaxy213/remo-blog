package com.remo.user.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CommonConfig {

    /**
     * dozer配置
     */
    @Bean("dozerBeanMapper")
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer/dozer-mapping.xml"));
        return mapper;
    }
}
