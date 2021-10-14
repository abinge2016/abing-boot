package com.abinge.boot.staging.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class TraceIdFilter implements WebFilter {
    private static final String HEADER_TRACE_ID_KEY = "X-B3-TRACEID";
    private static final String MDC_TRACE_ID_KEY = "X-B3-TraceId";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Map<String, String> headers = exchange.getRequest().getHeaders().toSingleValueMap();
        String traceId = headers.get(HEADER_TRACE_ID_KEY);
        if (StringUtils.isNotBlank(traceId)) {
            MDC.put(MDC_TRACE_ID_KEY, traceId);
            exchange.getAttributes().put(MDC_TRACE_ID_KEY, traceId);
        } else if (!exchange.getRequest().getURI().getPath().contains("/actuator")) {
            log.warn("TRACE_ID not present in header: {}", exchange.getRequest().getURI());
        }
        return chain.filter(exchange);

    }


}