package com.remo.gateway.config;

import com.remo.gateway.keyResolver.HostAddrKeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean("hostAddrKeyResolver")
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }

//    @Bean("uriKeyResolver")
//    public UriKeyResolver uriKeyResolver() {
//        return new UriKeyResolver();
//    }
}
