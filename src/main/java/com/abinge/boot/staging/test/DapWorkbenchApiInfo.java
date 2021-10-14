/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.test;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2021/2/19 17:22
 */
@Data
public class DapWorkbenchApiInfo {
    private Long id;
    private String code;
    private String interfaceName;
    private String method;
    private String path;
    private String status;
    private String version;
    private String headers;
    private Integer businessType;
    private String businessTypeName;
    private String remark;
    private String response;
    private String body;
    private Long creatorId;
    private String creatorName;
    private Long modifierId;
    private String modifierName;
    private Date gmtModified;
}
