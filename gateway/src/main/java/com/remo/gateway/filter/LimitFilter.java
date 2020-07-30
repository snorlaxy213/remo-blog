package com.remo.gateway.filter;

import com.remo.gateway.pojo.vo.ResponseVo;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class LimitFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //todo 业务逻辑
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        if (true) {
            return chain.filter(exchange);
        } else {
            byte[] bytes = generateBytes("too many requests");
            response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private byte[] generateBytes(String msg) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setErrMsg(msg);
        return responseVo.toString().getBytes(StandardCharsets.UTF_8);
    }
}
