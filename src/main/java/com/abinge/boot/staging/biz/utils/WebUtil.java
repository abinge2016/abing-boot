/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.biz.utils;

import com.guahao.convention.exception.ServiceException;
import com.guahao.opensaas.gateway.biz.constants.ServicePipelineCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * web工具类
 */
@Slf4j
public class WebUtil {
    private static final int BUFFER_SIZE = 1024;

    public static Map<String, String> getAllNoJsonArgs(HttpServletRequest request) {
        Map<String, String> args = new LinkedHashMap();
        Map<String, String[]> parameters = WebUtil.getParameterMap(request);
        if (MapUtils.isEmpty(parameters)) {
            return args;
        }
        parameters.forEach((k, v) -> {
            String value = null;
            if (v != null && v.length > 0) {
                value = v[0];
            }
            args.put(k, value);
        });
        return args;
    }

    /**
     * 获取JSON报文体，返回类型保持与APPLICATION_FORM_URLENCODED相同
     *
     * @param request
     * @return
     */
    public static String getRequestJSON(HttpServletRequest request) {
        return parseRequestJSONBody(request);
    }


    private static Map<String, String[]> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> parameterMap = new LinkedHashMap<>();
        if (MapUtils.isNotEmpty(request.getParameterMap())) {
            parameterMap.putAll(request.getParameterMap());
        }
        return parameterMap;
    }

    /**
     * 读取http请求中的JSON体
     *
     * @param request
     * @return
     */
    private static String parseRequestJSONBody(HttpServletRequest request) {
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                StringBuilder stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
                return stringBuilder.toString();
            }
        } catch (Exception ex) {
            log.warn("parseRequestJSONBody error", ex);
            throw new ServiceException(ServicePipelineCode.API_PARAMETER_BODY_JSON_ERROR);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.warn("parseRequestJSONBody buffer reader close error", ex);
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static MultiValueMap<String, String> parseInUrlParameters(String url, MultiValueMap<String, String> parameters) {
        if (StringUtils.isBlank(url)) {
            return parameters;
        }
        url = url.trim();
        String[] urlParts = url.split("\\?");
        //没有参数
        if (urlParts.length == 1) {
            return parameters;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 1) {
                result.put(keyValue[0], Arrays.asList(StringUtils.EMPTY));
            }else {
                result.put(keyValue[0], Arrays.asList(keyValue[1]));
            }
        }
        if (null != parameters && !parameters.isEmpty()) {
            result.putAll(parameters);
        }
        return result;
    }
}
