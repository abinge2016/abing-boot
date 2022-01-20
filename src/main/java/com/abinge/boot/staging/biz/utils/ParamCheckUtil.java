package com.abinge.boot.staging.biz.utils;


import com.abinge.boot.staging.biz.constants.ServiceCode;
import com.abinge.boot.staging.biz.constants.ServiceException;
import com.abinge.boot.staging.biz.constants.ServicePipelineCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 参数校验
 *
 */
public class ParamCheckUtil {
    public static final List<String> SIGN_HEAD_LIST = Arrays.asList("appkey", "timestamp", "version", "message-id");

    public static void checkCommonHeader(String requestMethod, String contentType, Map<String, String> headers){
        if ("POST".equals(requestMethod)){
            checkBlank(contentType, String.format(ServicePipelineCode.API_PARAM_IS_BLANK.message(), "Content-Type"));
        }
        SIGN_HEAD_LIST.stream().forEach(headerName -> {
            checkBlank(headers.get(headerName),String.format(ServicePipelineCode.API_PARAM_IS_BLANK.message(),headerName));
        });
    }

    public static void buildFail(ServicePipelineCode servicePipelineCode) {
        throw new ServiceException(servicePipelineCode.code(), servicePipelineCode.message());
    }

    /**
     * 校验表达式，为true抛出异常(状态码CLOUD_26,参数校验异常)
     *
     * @param exp
     * @param message
     */
    public static void checkFalse(boolean exp, String message) {
        if (exp) {
            throw new ServiceException(ServicePipelineCode.API_PATH_FORMAT_ERROR.code(), message);
        }
    }

    /**
     * 校验表达式，为true抛出异常
     *
     * @param exp
     * @param code
     * @param message
     */
    public static void checkFalse(boolean exp, ServiceCode code, String message) {
        if (exp) {
            throw new ServiceException(code.code(), message);
        }
    }

    /**
     * 校验表达式，为true抛出异常
     *
     * @param exp
     * @param code
     */
    public static void checkFalse(boolean exp, ServiceCode code) {
        if (exp) {
            throw code.failure();
        }
    }

    /**
     * 校验对象是否为空
     *
     * @param object
     * @param message
     */
    public static void checkNull(Object object, String message) {
        checkFalse(Objects.isNull(object), message);
    }

    /**
     * 校验字符串是否为空
     *
     * @param str
     * @param message
     */
    public static void checkBlank(String str, String message) {
        checkFalse(StringUtils.isBlank(str), message);
    }

    /**
     * 校验集合是否为空
     *
     * @param collection
     * @param message
     */
    public static void checkEmpty(Collection collection, String message) {
        checkFalse(CollectionUtils.isEmpty(collection), message);
    }

    /**
     * 校验对象是否为空
     *
     * @param object
     * @param code
     */
    public static void checkNull(Object object, ServiceCode code) {
        checkFalse(Objects.isNull(object), code);
    }

    /**
     * 校验字符串是否为空
     *
     * @param str
     * @param code
     */
    public static void checkBlank(String str, ServiceCode code) {
        checkFalse(StringUtils.isBlank(str), code);
    }

    /**
     * 校验id
     *
     * @param id
     */
    public static void checkId(Long id, String message) {
        checkFalse((id == null || id < 1), message);
    }

}
