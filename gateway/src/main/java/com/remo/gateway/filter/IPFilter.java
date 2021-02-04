package com.remo.gateway.filter;

import com.remo.gateway.build.GatewayDirector;
import com.remo.gateway.pojo.vo.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * description: 网关拦截
 * create by: YangLinWei
 * create time: 2020/5/20 9:10 上午
 */
@Component
public class IPFilter implements GatewayFilter, Ordered {

    @Autowired
    private GatewayDirector gatewayDirector;

    /**
     * 获取Ip地址
     *
     * @param request
     * @return ip address
     */
    public static String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String ipAddress = getIpAddress(request);
        if (StringUtils.isNotEmpty(ipAddress)) {
            return chain.filter(exchange);
        } else if (gatewayDirector.director(ipAddress)) {
            byte[] bytes = generateBytes("The ip address is block");
            response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        } else {
            byte[] bytes = generateBytes("Unable to get the ip address");
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

