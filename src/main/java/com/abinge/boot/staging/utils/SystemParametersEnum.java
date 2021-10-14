package com.abinge.boot.staging.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum SystemParametersEnum {
    /**
     * 开放平台系统参数
     */
    METHOD("method"), FORMAT("format"), APPKEY("appkey"), APPSECRET("appsecret"), ACCESSTOKEN("accessToken"), V(
        "v"), TIMESTAMP("timestamp"), SOURCE("source"), REQIP("reqIp"), SIGN("sign"), SERVICENAME(
        "serviceName"), JS_SIGN("jssign"), PAGE_URL("pageurl"), NONCESTR("noncestr"), ORANGE_UUID(
        "traceId"), X_ENV_STAGE("X-Env-Stage"),

    X_DUBBO_TAG("x-global-router-tag"),

    X_GT_B_BIZ_PARENT_SOURCE_ID("x-gt-b-biz-parent-source-id"),

    X_GT_B_BIZ_SOURCE_ID("x-gt-b-biz-source-id"),
    SIGN_VERSION("version"),
    PRODUCT_CODE("product-code"),
    MESSAGE_ID("message-id"),
    CONTENT_MD5("content-md5"),
    ;

    private String name;

    SystemParametersEnum(String name) {
        this.name = name;
    }

    public static boolean isExists(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        SystemParametersEnum[] spenums = SystemParametersEnum.values();
        for (SystemParametersEnum spenum : spenums) {
            if (spenum.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static List<String> getNames() {
        List<String> list = new ArrayList<>();
        for (SystemParametersEnum item : SystemParametersEnum.values()) {
            list.add(item.name);
        }
        return list;
    }
}