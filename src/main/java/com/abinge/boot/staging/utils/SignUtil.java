/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.utils;

import com.abinge.boot.staging.exceptions.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放平台签名工具类
 *
 * @author yinwei
 * @version V1.0
 * @since 2018-12-06 17:40
 */
@Slf4j
public class SignUtil {

    public static String sign(Map<String, String> headers, Map<String, String> parameters, String appSecret) {
        if (null == headers) {
            headers = new HashMap<>();
        }
        if (null == parameters) {
            parameters = new HashMap<>();
        }
        String sortStr = ParameterSortToStr.getSortParameter(headers, parameters);
        log.info("sortStr：{}", sortStr);
        String target = SystemParametersEnum.APPSECRET.getName() + sortStr + appSecret;
        log.info("target：{}", target);
        return sign(target);
    }

    private static String sign(String target) {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        try {
            byte[] infoMd5 = Md5Utils.encryptMD5(target.getBytes("UTF-8"));
            return Md5Utils.byte2hex(infoMd5);
        } catch (Exception e) {
            throw new BizException("API签名异常", e);
        }
    }
}
