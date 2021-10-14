package com.abinge.boot.staging.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求处理
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2020/9/29 13:38
 */
@Slf4j
public class RequestUtils {
    private static final String JSON_KEY = "requestBody";

    public static Map<String, String[]> getParameterMapIncludeJSON(HttpServletRequest request){
        return ContentType.APPLICATION_JSON.getMimeType().equals(request.getContentType()) ? getRequestJSONBody(request) : request.getParameterMap();
    }

    private static Map<String, String[]> getRequestJSONBody(HttpServletRequest request){
        Map<String, String[]> result = new HashMap<>();
        JSONObject jsonObject = parseRequestJSONBody(request);
        if (CollectionUtils.isEmpty(jsonObject)){
            return result;
        }
        JSONObject jsonBody = jsonObject.getJSONObject(JSON_KEY);
        if (CollectionUtils.isEmpty(jsonBody)){
            return result;
        }
        for (String key : jsonBody.keySet()){
            result.put(key,new String[]{jsonBody.getString(key)});
        }
        return result;
    }

    /**
     * 读取http请求中的json体
     * @param request
     * @return
     */
    private static JSONObject parseRequestJSONBody(HttpServletRequest request){
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                StringBuilder stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
                if (StringUtils.isNotBlank(stringBuilder)){
                    return JSON.parseObject(stringBuilder.toString());
                }
            }
        } catch (Exception ex) {
            log.warn("parseRequestJSONBody error",ex);
//            throw new ServiceException(ApplicationCode.INVALID_PARAMETER_ERROR.code(),"JSON报文解析错误");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.warn("parseRequestJSONBody buffer reader close error",ex);
//                    throw new ServiceException(ApplicationCode.INVALID_PARAMETER_ERROR.code(),"JSON报文解析错误");
                }
            }
        }
        return new JSONObject();
    }





    public static Object getBody(HttpServletRequest request){
        return ContentType.APPLICATION_JSON.getMimeType().equals(request.getContentType()) ? getJSONBody(request) : getParameters(request);
    }
    /**
     * 获取http请求的parameterMaps
     *
     * @param request
     * @return
     */
    public static Object getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (null == parameterMap || parameterMap.isEmpty()) {
            return StringUtils.EMPTY;
        }
        Map<String, Object> result = Maps.transformValues(parameterMap, value -> null != value && value.length > 0 ? parseString(value[0]) : StringUtils.EMPTY);
        return result;
    }

    private static Object parseString(String sourceStr){
        if (StringUtils.isNotBlank(sourceStr)){
            try{
                return JSON.parseObject(sourceStr);
            } catch (Exception e){
                try {
                    return JSON.parseArray(sourceStr);
                } catch (Exception ex){
                    return sourceStr;
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 读取http请求中的json体
     * @param request
     * @return
     */
    public static Object getJSONBody(HttpServletRequest request){
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                StringBuilder stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
                return parseString(stringBuilder.toString());
            }
        } catch (Exception ex) {
            log.error("getJSONBody error",ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.error("getJSONBody error",ex);
                }
            }
        }
        return new JSONObject();
    }
}
