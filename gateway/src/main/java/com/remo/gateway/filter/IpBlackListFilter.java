package com.remo.gateway.filter;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remo.gateway.build.GatewayDirector;
import com.remo.gateway.pojo.vo.ResponseVo;
import com.remo.gateway.utils.GatewayUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * description: 网关拦截
 */
@Component
public class IpBlackListFilter implements GlobalFilter, Ordered {

    @Autowired
    private GatewayDirector gatewayDirector;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String ipAddress = GatewayUtil.getIpAddress(request);
        Boolean isBlockIp = gatewayDirector.director(ipAddress);
        if (StringUtils.isNotEmpty(ipAddress) && !isBlockIp) {
            return chain.filter(exchange);
        } else if (isBlockIp) {
            String errorMeg = "The ip address is block";
//            byte[] bytes = errorMeg.getBytes(StandardCharsets.UTF_8);
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBuffer buffer = null;
            try {
                buffer = exchange.getResponse().bufferFactory().wrap(generateBytes(errorMeg));
            } catch (JsonProcessingException e) {
                response.writeWith(Flux.just(buffer));
            }
            return response.writeWith(Flux.just(buffer));
        } else {
            String errorMeg = "Unable to get the ip address";
//            byte[] bytes = errorMeg.getBytes(StandardCharsets.UTF_8);
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBuffer buffer = null;
            try {
                buffer = exchange.getResponse().bufferFactory().wrap(generateBytes(errorMeg));
            } catch (JsonProcessingException e) {
                response.writeWith(Flux.just(buffer));
            }
            return response.writeWith(Flux.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private byte[] generateBytes(String msg) throws JsonProcessingException {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(msg);
        return mapper.writeValueAsString(responseVo).getBytes(StandardCharsets.UTF_8);
    }

}

