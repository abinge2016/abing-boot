/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.biz.constants;

/**
 * Pipeline 管道上下文 传递数据
 */
public interface PipeAttrConstants {

    /**
     * API 是否用户授权
     */
    String OPEN_API_IS_AUTH = "open:api:auth";

    /**
     * 是否Saas应用跳转
     */
    String OPEN_IS_SAAS_REDIRECT = "open:saas:redirect";

    /**
     * API 用户授权令牌
     */
    String OPEN_API_ACCESS_TOKEN = "open:api:access:token";

    /**
     * API 是否需要UC用户对B端的授权
     */
    String OPEN_API_IS_BUSINESS_AUTH = "open:api:business:auth";

    /**
     * API UC用户对B端的授权令牌
     */
    String OPEN_API_OPEN_TOKEN = "open:api:open:token";

    /**
     * API 用户userId
     */
    String OPEN_API_USER_ID = "open:api:user:id";

    /**
     * API 参数加解密
     */
    String OPEN_API_REQ_SECURITY = "open:api:req:security";

    String OPEN_API_RES_SECURITY = "open:api:res:security";


    /**
     * API 请求参数
     */
    String OPEN_API_REQUEST_ARGS = "open:api:request:args";

    /**
     * API 基础信息（配置）
     */
    String OPEN_API_BASIC = "open:api:basic";
}
