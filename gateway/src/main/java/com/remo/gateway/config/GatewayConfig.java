package com.remo.gateway.config;

import com.remo.gateway.resolver.HostAddrKeyResolver;
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

    // Java写Route的方式, 可参考
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/consulserver/**")
//                        .filters(f -> f.stripPrefix(1)
//                                .filter(new LimitFilter(10, 1, Duration.ofSeconds(1)))
//                        )
//                        .uri("lb://consulserver")
//                        .order(0)
//                        .id("throttle_customer_service")
//                ).build();
//    }
}
