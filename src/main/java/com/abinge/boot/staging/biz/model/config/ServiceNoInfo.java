package com.abinge.boot.staging.biz.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 医院号相关配置
 *
 * @author chenbj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceNoInfo implements Serializable {
    /**
     * 医院号
     */
    private String serviceNo;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     * 上线状态 0未上线 1已上线 2已下线
     */
    private Integer onlineStatus;
    /**
     * 域名地址
     */
    private  String domainHost;

    /**
     * 访问凭证，这里暂取一个用于加签使用
     */
    private AccessInfo accessInfo;

    /**
     * 访问凭证信息，key：appkey，value：详细的访问凭证信息
     */
    private Map<String, AccessInfo> accessMap;

    /**
     * 资源明细，key：resourceCode，value：详细的资源信息
     */
    private Map<String, ResourceInfo> resourceMap;
    /**
     * 服务号相关配置
     */
    private Map<String, String> serviceNoConfigs;
}
