package com.remo.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * dozer配置
 *
 * @author Jules
 * @date 2019/8/28
 */
@Configuration
public class CommonConfig {

    /**
     * dozer配置
     */
    @Bean
    public DozerBeanMapper mapper(){
        List<String> mappingFiles = Arrays.asList(
                "dozer/dozer-mapping.xml"
        );
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }

}
