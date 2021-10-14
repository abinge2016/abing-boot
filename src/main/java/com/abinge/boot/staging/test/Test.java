/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.test;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2021/2/24 19:15
 */
public class Test {

    public static void main(String[] args) {
        ApplicationConfigCache applicationConfigCache = new ApplicationConfigCache();
        String keys = FileUtil.readUtf8String(new File("20210518_dubbo.json"));
        List<String> keyList = JSON.parseArray(keys, String.class);
        for (String key : keyList){
            testDubboCache(applicationConfigCache, key);
        }


    }

    private static GenericService testDubboCache(ApplicationConfigCache applicationConfigCache, String key){
        ReferenceConfig<GenericService> referenceConfig;
        GenericService genericService;
        try {
            referenceConfig = applicationConfigCache.get(key);
            if (Objects.isNull(referenceConfig) || StringUtils.isEmpty(referenceConfig.getInterface())) {
                applicationConfigCache.invalidate(key);
                referenceConfig = applicationConfigCache.initRef(key);
            }
            genericService = referenceConfig.get();
        } catch (Exception ex) {
            applicationConfigCache.invalidate(key);
            referenceConfig = applicationConfigCache.initRef(key);
            genericService = referenceConfig.get();
        }
        return genericService;
    }
}
