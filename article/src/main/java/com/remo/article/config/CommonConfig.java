package com.remo.article.config;

import org.dozer.DozerBeanMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * SpringBoot配置
 *
 * @author Vino
 * @date 2019/8/28
 */
@Configuration
public class CommonConfig {

    /**
     * dozer配置
     */
    @Bean("dozerBeanMapper")
    public DozerBeanMapper dozerBeanMapper() {
        List<String> mappingFiles = Arrays.asList(
                "dozer/dozer-mapping.xml"
        );
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }

    @Bean("restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
