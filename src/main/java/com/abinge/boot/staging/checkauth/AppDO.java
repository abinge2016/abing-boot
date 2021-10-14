/**
 * Project: frog-biz-dal
 * <p>
 * File Created at 2015年5月15日
 * <p>
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.abinge.boot.staging.checkauth;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangbin
 * @Type AppsDO
 * @Desc
 * @date 2015年5月15日
 * @Version V1.0
 */
public class AppDO implements Serializable {
    private static final long serialVersionUID = 756421175463154812L;
    // 主键id
    private Long id;
    // 授权有效时间 授权周期类型：0-长期 1-短期
    private String periodTimeType;
    // 授权有效时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vaildTime;
    // 页面应用名称
    private String memo;
    // 应用英文名称
    private String appName;
    // 应用的对接人
    private String appOwner;
    // 回调地址
    private String callbackUrl;
    // 应用key
    private String appKey;
    // 应用密码
    private String appSecret;
    // 审核状态：CheckStatusEnum
    private Integer status;
    // 所采用算法  md5  sha256
    private String algorithmType;
    // 创建时间
    private Date gmtCreated;
    // 修改时间
    private Date gmtModified;
    // 是否开启 0-是  1-否
    private Integer enabled;
    //旧秘钥
    private String oldAppSecret;
    // 秘钥过期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date secretExpiredTime;
    // OA工单
    private String workOrder;
    // 秘钥重置状态
    private Integer secretResetStatus;

    // ===== 不关注字段 ======== //
    // 验证方式：0 签名，1 加密，2：签名加密，3：无。
    private String validateType;
    // 创建人ID
    private Long createUserId;
    // 审批意见
    private String applyResult;
    // 审核人ID
    private Long checkerId;
    // 审核时间
    private Date checkDate;
    // kano id
    private String kanoId;
    // 应用流量控制
    private Integer flowControlNum;
    // 创建人 loginId账号
    private String createrLoginId;
    // 修改人 loginId账号
    private String modifierLoginId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVaildTime() {
        if (Objects.isNull(vaildTime)) {
            return getDeadline();
        }
        return vaildTime;
    }

    public void setVaildTime(Date vaildTime) {
        this.vaildTime = vaildTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(String appOwner) {
        this.appOwner = appOwner;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public String getApplyResult() {
        return applyResult;
    }

    public void setApplyResult(String applyResult) {
        this.applyResult = applyResult;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getKanoId() {
        return kanoId;
    }

    public void setKanoId(String kanoId) {
        this.kanoId = kanoId;
    }

    public Integer getFlowControlNum() {
        return flowControlNum;
    }

    public void setFlowControlNum(Integer flowControlNum) {
        this.flowControlNum = flowControlNum;
    }

    public String getPeriodTimeType() {
        return periodTimeType;
    }

    public void setPeriodTimeType(String periodTimeType) {
        this.periodTimeType = periodTimeType;
    }

    private static Date getDeadline() {
        String s = "2099-12-31";
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            return new Date("2099-12-31");
        }
    }

    public String getCreaterLoginId() {
        return createrLoginId;
    }

    public void setCreaterLoginId(String createrLoginId) {
        this.createrLoginId = createrLoginId;
    }

    public String getModifierLoginId() {
        return modifierLoginId;
    }

    public void setModifierLoginId(String modifierLoginId) {
        this.modifierLoginId = modifierLoginId;
    }

    public String getOldAppSecret() {
        return oldAppSecret;
    }

    public void setOldAppSecret(String oldAppSecret) {
        this.oldAppSecret = oldAppSecret;
    }

    public Date getSecretExpiredTime() {
        return secretExpiredTime;
    }

    public void setSecretExpiredTime(Date secretExpiredTime) {
        this.secretExpiredTime = secretExpiredTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public Integer getSecretResetStatus() {
        return secretResetStatus;
    }

    public void setSecretResetStatus(Integer secretResetStatus) {
        this.secretResetStatus = secretResetStatus;
    }
}
