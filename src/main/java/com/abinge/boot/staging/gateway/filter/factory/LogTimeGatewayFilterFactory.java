package com.abinge.boot.staging.gateway.filter.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 此处类名定义特别注意，必须以GatewayFilterFactory结果，否则将会无法正确加载
 * @author abinge
 */
@Slf4j
@Component
public class LogTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<LogTimeGatewayFilterFactory.Config> {
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
    private static final String KEY = "enabled";

    public LogTimeGatewayFilterFactory() {
        super(Config.class);
        log.info("Load LogTimeFilterFactory success");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(ELAPSED_TIME_BEGIN, Instant.now().toEpochMilli());
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                if (Objects.nonNull(startTime)){
                    log.info("{}:{}ms",exchange.getRequest().getURI().getPath(),(Instant.now().toEpochMilli() - startTime));
                }
            }));
        };
    }

    public static class Config {
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
