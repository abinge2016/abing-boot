package com.abinge.boot.staging.gateway.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 此处类名定义特别注意，必须以GatewayFilterFactory结果，否则将会无法正确加载
 * @author abinge
 */
@Slf4j
@Component
public class CheckSignGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckSignGatewayFilterFactory.Config> {
    private static final String AUTHORIZE_TOKEN = "access-token";

    private static final String KEY = "enabled";

    public CheckSignGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded GatewayFilterFactory [CheckSign]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()){
                return chain.filter(exchange);
            }
            ServerHttpRequest request = exchange.getRequest();
            MultiValueMap<String, String> queryParams = request.getQueryParams();



            HttpHeaders headers = request.getHeaders();
            String accessToken = headers.getFirst(AUTHORIZE_TOKEN);
            // TODO 校验accessToken
            if (StringUtils.isBlank(accessToken)){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
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
