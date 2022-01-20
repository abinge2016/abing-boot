package com.abinge.boot.staging.biz.utils;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;

import java.net.URI;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author chenbj
 * 基于webflux的webclient实现的异步http客户端工具
 */
@Slf4j
public class WebClientUtil {


    private WebClient client;

    public WebClientUtil() {
        client = webClient();
    }

    private ReactorResourceFactory clientResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        factory.setConnectionProvider(ConnectionProvider.builder("staging")
                .maxConnections(200)
                .pendingAcquireTimeout(Duration.ofMillis(3000))
                .maxIdleTime(Duration.ofMillis(3000))
                .lifo()
                .build());
        factory.setLoopResources(LoopResources.create("staging", 1, 20, true));
        return factory;
    }

    private WebClient webClient() {
        Function<HttpClient, HttpClient> mapper = client ->
                client.tcpConfiguration(c ->
                        c.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                                .option(ChannelOption.TCP_NODELAY, true)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS))
                                )
                );
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(clientResourceFactory(), mapper)).build();
    }

    public Mono<String> doGet(String url, Map<String, Object> header, Map<String, Object> query) {
        return client.get()
                .uri(url, uriBuilder -> {
                    URI uri = uriBuilder.queryParams(buildQueryParams(query)).build();
                    log.info("========> WebClientUtil#doGet请求开始, url: {}", uri.toString());
                    return uri;
                }).headers(buildHeaders(header))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPostForm(String url, Map<String, Object> header, Map<String, Object> parameter) {
        log.info("========> WebClientUtil#doPostForm请求开始, url:{}, header:{}, query:{}",
                url, JSON.toJSONString(header), JSON.toJSONString(parameter));
        LinkedMultiValueMap<String, String> queryParams = buildQueryParams(parameter);
        return client.post()
                .uri(url, uriBuilder -> uriBuilder.build())
                .headers(buildHeaders(header))
                .bodyValue(queryParams)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPutForm(String url, Map<String, Object> header, Map<String, Object> parameter) {
        log.info("========> WebClientUtil#doPutForm请求开始, url:{}, header:{}, query:{}",
                url, JSON.toJSONString(header), JSON.toJSONString(parameter));
        LinkedMultiValueMap<String, String> queryParams = buildQueryParams(parameter);

        return client.put()
                .uri(url, uriBuilder -> uriBuilder.build())
                .headers(buildHeaders(header))
                .bodyValue(queryParams)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPostJSON(String url, Map<String, Object> header, String json) {
        log.info("========> WebClientUtil#doPostJSON请求开始, url:{}, header:{}, json:{}",
                url, JSON.toJSONString(header), json);

        return client.post()
                .uri(url)
                .headers(buildHeaders(header))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPutJSON(String url, Map<String, Object> header, String json) {
        log.info("========> WebClientUtil#doPutJSON请求开始, url:{}, header:{}, json:{}",
                url, JSON.toJSONString(header), json);
        return client.put()
                .uri(url)
                .headers(buildHeaders(header))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPostXML(String url, Map<String, Object> header, String xml) {
        log.info("========> WebClientUtil#doPostXML请求开始, url:{}, header:{}, xml:{}",
                url, JSON.toJSONString(header), xml);
        return client.post()
                .uri(url)
                .headers(buildHeaders(header))
                .contentType(MediaType.APPLICATION_XML)
                .bodyValue(xml)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> doPutXML(String url, Map<String, Object> header, String xml) {
        log.info("========> WebClientUtil#doPutXML请求开始, url:{}, header:{}, xml:{}",
                url, JSON.toJSONString(header), xml);
        return client.put()
                .uri(url)
                .headers(buildHeaders(header))
                .contentType(MediaType.APPLICATION_XML)
                .bodyValue(xml)
                .retrieve()
                .bodyToMono(String.class);
    }

    private static LinkedMultiValueMap<String, String> buildQueryParams(Map<String, Object> queryParams) {
        LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        if (!CollectionUtils.isEmpty(queryParams)) {
            Iterator<Map.Entry<String, Object>> headerIterator = queryParams.entrySet().iterator();
            headerIterator.forEachRemaining(e -> {
                multiValueMap.add(e.getKey(), String.valueOf(e.getValue()));
            });
        }
        return multiValueMap;
    }

    private static Consumer<HttpHeaders> buildHeaders(Map<String, Object> header) {
        return httpHeaders -> {
            if (!CollectionUtils.isEmpty(header)) {
                Iterator<Map.Entry<String, Object>> headerIterator = header.entrySet().iterator();
                headerIterator.forEachRemaining(e -> {
                    httpHeaders.add(e.getKey(), String.valueOf(e.getValue()));
                });
            }
        };
    }
}
