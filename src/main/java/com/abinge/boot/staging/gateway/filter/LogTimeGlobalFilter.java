package com.abinge.boot.staging.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Objects;

/**
 * 记录调用时间的filter
 *
 * @author abinge
 */
@Slf4j
@Component
public class LogTimeGlobalFilter implements GlobalFilter, Ordered {
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, Instant.now().toEpochMilli());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
            if (Objects.nonNull(startTime)) {
                log.info("{} elapsed {}ms", exchange.getRequest().getURI().getPath(), (Instant.now().toEpochMilli() - startTime));
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
