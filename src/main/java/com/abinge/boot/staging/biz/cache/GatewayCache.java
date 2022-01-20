package com.abinge.boot.staging.biz.cache;

import com.abinge.boot.staging.biz.constants.GlobalConstants;
import com.abinge.boot.staging.biz.constants.ServicePipelineCode;
import com.abinge.boot.staging.biz.manager.OpenSaasAccessManager;
import com.abinge.boot.staging.biz.manager.OpenSaasOrgManager;
import com.abinge.boot.staging.biz.model.config.OspDomainConfiguration;
import com.abinge.boot.staging.biz.model.config.AccessInfo;
import com.abinge.boot.staging.biz.model.config.AppInfo;
import com.abinge.boot.staging.biz.model.config.DomainInfo;
import com.abinge.boot.staging.biz.model.config.ServiceNoInfo;
import com.abinge.boot.staging.biz.utils.CacheResultSupporter;
import com.abinge.boot.staging.biz.utils.ParamCheckUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 域名相关缓存操作
 */
@Slf4j
@Component
public class GatewayCache {

    @Resource
    OspDomainConfiguration ospDomainConfiguration;

    @Resource
    OpenSaasOrgManager openSaasOrgManager;

    @Resource
    OpenSaasAccessManager openSaasAccessManager;

    /**
     * 域名缓存，包含如下三种：
     * （1）平台公共域名，不随机构医院号配置变动，可能会根据不同区域而前缀不同：开放API统一域名、运营端域名、开放平台主站域名、开放平台控制台域名
     * （2）患者端域名，随机构医院号配置变动：健康门户-移动端域名
     * （3）医生端域名，随机构服务号配置变动：医院机构his系统对接配置域名
     */
    private Cache<String, DomainInfo> DOMAIN_CACHE;
    /**
     * 医院号缓存，key：医院号serviceNo，value：医院号信息及相关配置（包含医院号信息，医院号状态，访问凭证，拥有的资源列表）
     */
    private Cache<String, ServiceNoInfo> SERVICE_NO_CACHE;
    /**
     * 访问凭证缓存，key：appKey，value：访问凭证信息（秘钥，有效区间，算法类型）
     */
    private Cache<String, AccessInfo> APP_KEY_CACHE;

    /**
     * 应用信息缓存，key：应用唯一标识appCode，value：应用信息（应用状态，应用联合登录地址，回调地址）
     */
    private Cache<String, AppInfo> ISV_APP_CACHE;

    /**
     * 请求message-id防重放本地缓存，2.5分钟内，相同appKey和message-id不能重复
     * key：appKey:message-id
     */
    private Cache<String, String> MESSAGE_ID_CACHE;


    @PostConstruct
    public void init() {
        initDomainCache();
        initServiceNoCache();
        initAppKeyCache();
        initIsvAppCache();
        initMessageIdCache();
    }

    public DomainInfo getDomainInfo(String domain) {
        try {
            // 首先查询公共域名
            DomainInfo domainInfo = queryCommonDomain(domain);
            if (Objects.nonNull(domainInfo)) {
                return domainInfo;
            }
            // 其次查询医院号域名
            domainInfo = getCache(DOMAIN_CACHE, domain, () -> openSaasOrgManager.queryServiceNoDomain(domain));
            ParamCheckUtil.checkFalse(Objects.isNull(domainInfo), ServicePipelineCode.DOMAIN_NOT_FOUND);
            return domainInfo;
        } catch (Exception e) {
            log.info("缓存获取域名失败，domain:{}", domain, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.DOMAIN_NOT_FOUND);
        }
        return null;
    }

    public AccessInfo getAccessInfo(String appKey) {
        try {
            // 首先查询公共域名配置的访问凭证
            AccessInfo accessInfo = queryCommonDomainAccess(appKey);
            if (Objects.nonNull(accessInfo)) {
                return accessInfo;
            }
            accessInfo = getCache(APP_KEY_CACHE, appKey, () -> openSaasAccessManager.queryAccessInfo(appKey));
            ParamCheckUtil.checkFalse(Objects.isNull(accessInfo), ServicePipelineCode.NO_AUTH_ERROR, "访问凭证不存在");
            return accessInfo;
        } catch (Exception e) {
            log.info("缓存获取访问凭证失败，appKey:{}", appKey, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.NO_AUTH_ERROR);
        }
        return null;
    }

    public ServiceNoInfo getServiceNoInfo(String serviceNo) {
        try {
            ServiceNoInfo serviceNoInfo = getCache(SERVICE_NO_CACHE, serviceNo, () -> openSaasOrgManager.queryServiceNoInfo(serviceNo));
            ParamCheckUtil.checkFalse(Objects.isNull(serviceNoInfo), ServicePipelineCode.NO_AUTH_ERROR, "对应医院号不存在");
            return serviceNoInfo;
        } catch (Exception e) {
            log.info("缓存获取医院号信息失败，serviceNo:{}", serviceNo, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.NO_AUTH_ERROR);
        }
        return null;
    }

    public AppInfo getIsvAppInfo(String appCode) {
        try {
            AppInfo isvAppInfo = getCache(ISV_APP_CACHE, appCode, () -> openSaasAccessManager.queryAppInfo(appCode));
            ParamCheckUtil.checkFalse(Objects.isNull(isvAppInfo), ServicePipelineCode.APP_NOT_FOUND);
            return isvAppInfo;
        } catch (Exception e) {
            log.info("缓存获取应用信息失败，appCode:{}", appCode, e);
            ParamCheckUtil.buildFail(ServicePipelineCode.APP_NOT_FOUND);
        }
        return null;
    }

    /**
     * 2.5分钟内，相同appKey和message-id不能重复
     * @param appKey
     * @param messageId
     * @return
     */
    public Boolean messageIdRepeated(String appKey, String messageId) {
        String cacheKey = buildMessageCacheKey(appKey, messageId);
        String cacheValue = MESSAGE_ID_CACHE.getIfPresent(cacheKey);
        if (Objects.nonNull(cacheValue)) {
            return Boolean.TRUE;
        }
        MESSAGE_ID_CACHE.put(cacheKey, cacheKey);
        return Boolean.FALSE;
    }


    private String buildMessageCacheKey(String appKey, String messageId) {
        return new StringBuilder(appKey).append(":").append(messageId).toString();
    }

    private DomainInfo queryCommonDomain(String domain) {
        List<DomainInfo> commonDomain = ospDomainConfiguration.getCommon();
        if (CollectionUtils.isNotEmpty(commonDomain)) {
            for (DomainInfo domainInfo : commonDomain) {
                if (domain.equals(domainInfo.getHost())) {
                    return domainInfo;
                }
            }
        }
        return null;
    }

    private AccessInfo queryCommonDomainAccess(String appKey) {
        List<DomainInfo> commonDomain = ospDomainConfiguration.getCommon();
        if (CollectionUtils.isNotEmpty(commonDomain)) {
            for (DomainInfo domainInfo : commonDomain) {
                if (appKey.equals(domainInfo.getAppKey())) {
                    return AccessInfo.builder()
                            .appKey(appKey)
                            .appSecret(domainInfo.getAppSecret())
                            .build();
                }
            }
        }
        return null;
    }

    private <T> T getCache(Cache<String, T> cache, String key, CacheResultSupporter<T> support) throws ExecutionException {
        T data = cache.get(key, () -> {
            T obj = (T) support.execute();
            if (Objects.nonNull(obj)) {
                cache.put(key, obj);
            }
            return obj;
        });
        return data;
    }


    private void initDomainCache() {
        DOMAIN_CACHE = CacheBuilder.newBuilder()
                .maximumSize(256)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    private void initServiceNoCache() {
        SERVICE_NO_CACHE = CacheBuilder.newBuilder()
                .maximumSize(512)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    private void initAppKeyCache() {
        APP_KEY_CACHE = CacheBuilder.newBuilder()
                .maximumSize(1024)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    private void initIsvAppCache() {
        ISV_APP_CACHE = CacheBuilder.newBuilder()
                .maximumSize(1024)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    private void initMessageIdCache() {
        MESSAGE_ID_CACHE = CacheBuilder.newBuilder()
                .maximumSize(1024)
                .expireAfterWrite(GlobalConstants.MAX_MILLIS, TimeUnit.MILLISECONDS)
                .build();
    }


}
