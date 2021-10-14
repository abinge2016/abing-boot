/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.checkauth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2021/4/30 11:20
 */
@Data
@AllArgsConstructor
public class AppServiceExcel {
    private String appKey;
    private String appSecret;
    private String appName;
    private String serviceId;
}
