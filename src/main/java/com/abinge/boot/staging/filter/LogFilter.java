package com.abinge.boot.staging.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * @author abinge
 * @version 1.0.0
 * <p>Copyright Copyright (c) 2019/1/5</p>
 * @package com.abinge.boot.staging.interceptor
 * @description 日志记录拦截器
 * @date 2019/1/5 18:46
 */
@Component
@Slf4j
public class LogFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestURL = request.getURI().getPath();
        HttpMethod method = request.getMethod();
        Map<String, String> headers = request.getHeaders().toSingleValueMap();
        Map<String, String> queryMap = request.getQueryParams().toSingleValueMap();
        InetSocketAddress ip = request.getLocalAddress();
        log.info("IP：{}，method：{}, url：{}?{}, headers:{}, body:{}", ip, method, requestURL, queryMap, headers);
        return chain.filter(exchange);
    }
}
