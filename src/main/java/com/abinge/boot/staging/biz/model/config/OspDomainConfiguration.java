package com.abinge.boot.staging.biz.model.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * openSaas平台公共入口域名清单
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "guahao.opensaas.gateway.domains")
public class OspDomainConfiguration {
    /**
     * OpenSaas平台公共域名，不随机构医院号配置变动
     */
    List<DomainInfo> common;
    /**
     * 医生端域名
     * TODO 前期从网关配置文件中读取，后期需要根据机构的服务号进行配置变动
     */
    List<DomainInfo> doctor;
}
