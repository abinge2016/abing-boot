/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.model;

import com.google.common.collect.Maps;

import java.util.Map;


/**
 * @author yinwei
 * @version V1.0
 * @since 2020-06-23 13:46
 */
public class ApiParameter {

    /**
     * 参数、value
     */
    private Map<String, String[]> parameters;

    public Map<String, String> getParameters() {
        Map<String, String> requestParameter = Maps.newHashMap();

        if (null == parameters || parameters.isEmpty()) {
            return requestParameter;
        }

        parameters.forEach((k, v) -> {
            String value = null;
            if (v != null && v.length > 0) {
                value = v[0];
            }
            requestParameter.put(k, value);
        });

        return requestParameter;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }
}
