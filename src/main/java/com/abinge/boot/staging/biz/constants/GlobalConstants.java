/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.biz.constants;

public final class GlobalConstants {
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String DATE = "data";
    public static final String SUCCESS = "success";

    /**
     * 请求超时时间，用于校验请求头中的timestamp和message-id
     * timestamp：不能与当前系统时间相差2.5分钟
     * message-id：2.5分钟内，相同appKey和message-id不能重复
     */
    public static final Long MAX_MILLIS = 150 * 1000L;

    /**
     * 用户访问令牌
     */
    public static final String ACCESS_TOKEN = "weiyi-authtoken";
    /**
     * 医院号
     */
    public static final String SERVICE_NO = "service-no";
    /**
     * 应用code
     */
    public static final String APP_NO = "app-no";

    public static final String USER_ID ="userId";

    public static final String CONTENT_LENGTH ="content-length";

    public static final String HOST ="host";

}
