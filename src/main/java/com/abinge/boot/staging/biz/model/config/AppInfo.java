package com.abinge.boot.staging.biz.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * ISV应用信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppInfo implements Serializable {
    /**
     * 应用code
     */
    private String appCode;

    /**
     * 状态：0.待审核 1.审核中 2.上架 3.下架
     */
    private Integer appStatus;

    /**
     * 访问凭证信息，key：appkey，value：详细的访问凭证信息
     */
    Map<String, AccessInfo> accessMap;

    /**
     * 免密登录地址
     */
    private String loginUrl;

    /**
     * 消息通知地址
     */
    private String messageUrl;
}
