package com.abinge.boot.staging.biz.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公共入口域名相关配置信息
 *
 * @author chenbj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DomainInfo implements Serializable {
    /**
     * 域名
     */
    private String host;

    /**
     * 只有当loginAuth为ture时才有效
     * 该域名下，所属账号类型，0-患者C端，1-医生端D端，2-企业端B端
     */
    private Integer userType;
    /**
     * 该域名下，访问凭证ID
     */
    private String appKey;
    /**
     * 该域名下，访问凭证秘钥
     */
    private String appSecret;
    /**
     * 是否需要登录校验
     */
    private boolean loginAuth;
    /**
     * 是否使用域名配置的访问凭证
     */
    private boolean useConfigAccess;
    /**
     * 是否跳过验签
     */
    private boolean skipCheckSign;
    /**
     * 医院号/服务号
     * 当useConfigAccess为false时
     */
    private String serviceNo;
    /**
     * 根路径
     */
    private String rootPath;
}
