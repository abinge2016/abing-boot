/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.test;

import com.abinge.boot.staging.exceptions.BizException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * ReferenceConfig<GenericService> 缓存
 *
 * @author yinwei
 * @version V1.0
 * @since 2020-04-28 11:01
 */
public class ApplicationConfigCache {
    private static final Logger LOG = LoggerFactory.getLogger(
            ApplicationConfigCache.class);
    private static final String PROTOCOL = "dubbo";

    private static final int zkMaxCount = 500;

    /**
     * ReferenceConfig<GenericService> 实例缓存
     */
    private final LoadingCache<String, ReferenceConfig<GenericService>> referenceCache = CacheBuilder.newBuilder()
            .maximumWeight(zkMaxCount)
            .weigher((Weigher<String, ReferenceConfig<GenericService>>) (string, ReferenceConfig) -> getZkSize())
            .build(new CacheLoader<String, ReferenceConfig<GenericService>>() {
                @Override
                public ReferenceConfig<GenericService> load(final String key) {
                    return new ReferenceConfig<>();
                }
            });

    public ReferenceConfig<GenericService> get(final String key) {
        try {
            return referenceCache.get(key);
        } catch (ExecutionException e) {
            throw new BizException(e);
        }
    }

    public ReferenceConfig<GenericService> initRef(final String key) {
        try {
            ReferenceConfig<GenericService> referenceConfig = referenceCache.get(key);
            if (StringUtils.isNoneBlank(referenceConfig.getInterface())) {
                return referenceConfig;
            }
        } catch (ExecutionException e) {
            throw new BizException(e);
        }
        return build(key);
    }

    public ReferenceConfig<GenericService> build(final String key) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        // 泛型接口
        referenceConfig.setInterface(key);
        referenceConfig.setGeneric(true);
        // 重试次数
        referenceConfig.setRetries(3);
        // 超时时间
        referenceConfig.setTimeout(6000);

        referenceConfig.setUrl("http://localhost:8080");
        Object obj = referenceConfig.get();
        if (obj != null) {
            referenceCache.put(key, referenceConfig);
        }
        return referenceConfig;
    }

    public void invalidate(final String key) {
        referenceCache.invalidate(key);
    }

    private int getZkSize() {
        return (int) referenceCache.size();
    }
}
