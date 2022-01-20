/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.biz.constants;


public enum ServicePipelineCode implements ServiceCode {

    /*+++++++++++++++++++++++++++++ 鉴权相关 +++++++++++++++++++++++++++++++++++*/
    AUTH_ERROR("403001", "AUTH", "鉴权请求异常"),

    NO_AUTH_ERROR("403002", "AUTH", "鉴权失败"),

    ACCESS_NOT_FOUND("403002", "AUTH", "访问凭证不存在"),

    MESSAGE_ID_TIMEOUT("403003", "AUTH", "message-id已过期"),

    TIMESTAMP_TIMEOUT("403003", "AUTH", "timestamp已过期"),

    CONTENT_MD5_ERROR("403003", "SIGN", "content-md5校验失败"),

    SIGN_ERROR("403004", "SIGN", "签名失败"),


    /*+++++++++++++++++++++++++++++ 定义API参数异常码 +++++++++++++++++++++++++++++++++++*/
    // 说明：API接口参数暂不支持、格式错误以及解析错误等
    APP_NOT_FOUND("404401", "ROUTER", "API应用不存在"),

    DOMAIN_NOT_FOUND("404401", "ROUTER", "访问地址不存在"),

    APP_LOGIN_URL_BLANK("404402", "ROUTER", "应用免密登录地址不存在"),

    APP_BIZ_REDIRECT_URL_BLANK("404403", "ROUTER", "应用能力资源地址不存在"),

    HTTP_ROUTE_NOT_FOUND("404404", "ROUTE", "请求路径不存在"),



    /*+++++++++++++++++++++++++++++ 定义API参数异常码 +++++++++++++++++++++++++++++++++++*/
    // 说明：API接口参数暂不支持、格式错误以及解析错误等

    API_PARAMETER_FORMAT_ERROR("405001", "API", "API参数%s[%s]格式错误，请开发者注意检查！"),
    API_PARAMETER_NOT_SUPPORTED("405002", "API", "API参数%s[%s]暂不支持，请开发者注意检查！"),
    API_PARAMETER_IS_REQUIRED("405003", "API", "请求参数[%s]不能为空！"),
    API_PARAMETER_URL_VARIABLES_ERROR("405003", "API", "API URL参数非法，请开发者注意检查！"),
    API_PARAMETER_BODY_JSON_ERROR("405003", "API", "JSON报文解析错误！"),

    API_PARAM_IS_BLANK("405003", "API", "参数[%s]缺失"),

    API_PARAMETER_DECRYPT_ERROR("405003", "API", "请求参数解密异常！"),
    API_PARAMETER_ENCRYPT_ERROR("405003", "API", "响应结果加密异常！"),

    API_PATH_FORMAT_ERROR("406001", "API", "API 路径格式不正确，请开发者注意检查！"),
    API_PATH_INVALID("406002", "API", "API 路径当前不可用！"),
    API_SERVICE_ERROR("406003", "API", "API 不存在！"),
    API_ROUTER_ERROR("406004", "API", "API 编排步骤配置不存在或未生效！"),


    BIZ_SERVICE_RESULT_NULL("503000", "API", "result is empty"),

    BIZ_SERVICE_CALL_FAILED("503500", "API", "biz service call failed!"),

    // 接口超时
    TIMEOUT_EXCEPTION("503200", "API", "timeout!"),
    BIZ_SERVICE_RESULT_ERROR("503000", "API", "接口返回数据解析异常"),

    CONNECTION_EXCEPTION("503100", "API", "connection refused!"),

    INVALID_ACCESS_TOKEN_BLANK("2", "AUTH", "用户令牌weiyi-authtoken为空!"),
    AUTH_CODE_ERROR("2", "AUTH", "获取授权code失败"),

    INVALID_ACCESS_TOKEN_ERROR("2", "AUTH", "用户授权令牌accessToken解析失败,无法获取用户数据！"),
    INVALID_AUTH_CODE_ERROR("2", "AUTH", "授权CODE无效"),
    INVALID_AUTH_REFRESH_TOKEN_ERROR("2", "AUTH", "授权刷新令牌已无效"),
    AUTH_REFRESH_TOKEN_ERROR("2", "AUTH", "授权令牌刷新失败"),
    OPEN_UID_ERROR("2", "AUTH", "根据OpenUid获取用户信息失败"),
    CACHE_FUND("2", "AUTH", "缓存信息同步更新失败"),

    /*+++++++++++++++++++++++++++++ 机构参数异常码 +++++++++++++++++++++++++++++++++++*/
    SERVICE_NO_NOT_FUND("601001", "ORG", "机构医院号信息查询失败"),


    /*+++++++++++++++++++++++++++++ 文件上传相关异常码 +++++++++++++++++++++++++++++++++++*/
    UPLOAD_FILE_ERROR("701001", "FILE", "文件上传失败"),
    FILE_SUFFIX_ERROR("701002", "FILE", "文件格式不支持"),
    FILE_EXT_MAX_SIZE_ERROR("701003", "FILE", "文件超过最大限制"),

    /*+++++++++++++++++++++++++++++ 登录相关异常码 +++++++++++++++++++++++++++++++++++*/
    UNION_LOGIN_ERROR("801001", "LOGIN", "集成登录失败"),



    ;

    private String code;

    private String message;

    ServicePipelineCode(String code, String codeSign, String message) {
        this.code = "OPEN_" + codeSign + "_" + code;
        this.message = message;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
