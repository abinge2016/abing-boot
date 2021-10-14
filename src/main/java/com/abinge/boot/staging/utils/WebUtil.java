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
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具类
 *
 * @author yinwei
 * @version V1.0
 * @since 2018-12-06 17:53
 */
@Slf4j
public class WebUtil {
    public static String getSign(Map<String, String> headerMap, Map<String, String> parameterMap, String appSecret) {
        return SignUtil.sign(headerMap, parameterMap, appSecret);
    }

    public static String doPostJson(String url, Map<String, String> headerMap, String paramJson) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        setHttpHeader(headerMap, httpPost);

        httpPost.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        // 设置业务参数
        if (StringUtils.isNotBlank(paramJson)) {
            StringEntity requestEntity = new StringEntity(paramJson, CommonConstant.CHARSET);
            requestEntity.setContentEncoding(CommonConstant.CHARSET.toString());
            httpPost.setEntity(requestEntity);
        }
        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            log.info("http status code：{}", httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CommonConstant.CHARSET);

        } catch (IOException e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    throw new BizException("响应体消费异常", io);
                }
            }
            throw new BizException("API服务调用异常", e);
        }
    }

    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> parameters, String contentType) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        setHttpHeader(headerMap, httpPost);
        if (StringUtils.isNotBlank(contentType)) {
            httpPost.setHeader("Content-type", contentType);
        }

        // 设置业务参数
        List<NameValuePair> urlParameters = new ArrayList<>();
        setNameValuePair(parameters, urlParameters);
        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters, CommonConstant.CHARSET);
        httpPost.setEntity(postParams);
        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            log.info("http status code：{}", httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CommonConstant.CHARSET);
        } catch (IOException e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    throw new BizException("响应体消费异常", io);
                }
            }
            throw new BizException("API服务调用异常", e);
        }
    }

    public static String doGet(String url, Map<String, String> headerMap, Map<String, String> parameters, String contentType) {
        HttpGet httpGet = new HttpGet();
        // 设置请求头
        setHttpHeader(headerMap, httpGet);
        if (StringUtils.isNotBlank(contentType)) {
            httpGet.setHeader("Content-type", contentType);
        }
        String parms = StringUtils.EMPTY;
        if (!CollectionUtils.isEmpty(parameters)) {
            StringBuilder sb = new StringBuilder("?");
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                if (StringUtils.isNotBlank(entry.getValue())) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            parms = sb.substring(0, sb.length() - 1);
        }


        CloseableHttpResponse httpResponse = null;
        try {
            httpGet.setURI(URI.create(url + URLEncoder.encode(parms)));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpGet);
            int httpStatus = httpResponse.getStatusLine().getStatusCode();
            log.info("http status code：{}", httpStatus);
            return EntityUtils.toString(httpResponse.getEntity(), CommonConstant.CHARSET);
        } catch (Exception e) {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException io) {
                    throw new BizException("响应体消费异常", io);
                }
            }
            throw new BizException("API服务调用异常", e);
        }
    }

    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> parameters) {
        return doPost(url, headerMap, parameters, null);
    }

    private static void setHttpHeader(Map<String, String> headers, HttpRequest httpPost) {
        if (null == headers || headers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    // 设置业务参数，value为空的参数不向下层传递
    private static void setNameValuePair(Map<String, String> parameters, List<NameValuePair> urlParameters) {
        if (null == parameters || parameters.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                urlParameters.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
    }
}
