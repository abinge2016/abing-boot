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

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangbin
 * @Type ServicesDO
 * @Desc
 * @date 2015年5月15日
 * @Version V1.0
 */
public class ServicesDO implements Serializable {
    private static final long serialVersionUID = -1176192421289194052L;

    // 主键id
    private Long id;
    // 服务名称
    private String name;
    //服务中文名称
    private String serviceNameCn;
    // 命名空间
    private String namespace;
    // 服务协议
    private String serviceProtocol;
    // 服务url地址
    private String serviceUrl;
    // 服务接口类全名
    private String serviceInterface;
    // 服务方法
    private String serviceMethod;
    // 服务版本
    private String serviceVersion;
    // 服务参数
    private String serviceParam;
    // 服务返回结果
    private String returnResult;
    // 服务超时时间
    private Long serviceTimeout;
    // 服务负责人
    private String serviceOwner;
    // 服务状态 0:启用，1：停用
    private Integer status;
    //服务系统ID
    private Long providerId;
    //模板状态
    private Integer templateSts;
    //模板内容
    private String templateContent;
    // 创建时间
    private Date gmtCreated;
    // 修改时间
    private Date gmtModified;
    // 是否授权
    private Integer isAuth;
    // 创建人ID
    private Long serviceOwnerId;
    // jar文件名
    private String fileName;
    // 备注
    private String memo;
    // dubbo版本
    private String dubboVersion;
    // dubbo分组
    private String dubboGroup;
    // 服务隔离方式，1：不隔离，2：分组，3：版本，4：分组+版本,默认不隔离 1
    private Integer dubboInsulate;
    // dubbo调用方式，1：注册中心（zk），2：直联 ，默认注册中心 1
    private Integer dubboInvoke;
    // 返回结果json示例
    private String jsonExample;
    // 返回结果xml示例
    private String xmlExample;

    // HTTP协议方法：0、get 1、post
    private String httpMethod;
    // HTTP协议消息体类型：0、form 1、body
    private String httpEntityType;
    // 服务是否开放 0：开放  1：不开放
    private Integer isOpen;
    // 1:手动管理方式；2:注解管理方式
    private Integer manageType;
    // 产品编码
    private String productCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name.trim();
        }
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getServiceProtocol() {
        return serviceProtocol;
    }

    public void setServiceProtocol(String serviceProtocol) {
        this.serviceProtocol = serviceProtocol;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceParam() {
        return serviceParam;
    }

    public void setServiceParam(String serviceParam) {
        this.serviceParam = serviceParam;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public Long getServiceTimeout() {
        return serviceTimeout;
    }

    public void setServiceTimeout(Long serviceTimeout) {
        this.serviceTimeout = serviceTimeout;
    }

    public String getServiceOwner() {
        return serviceOwner;
    }

    public void setServiceOwner(String serviceOwner) {
        this.serviceOwner = serviceOwner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public Long getServiceOwnerId() {
        return serviceOwnerId;
    }

    public void setServiceOwnerId(Long serviceOwnerId) {
        this.serviceOwnerId = serviceOwnerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getJarName() {
        return this.getFileName();
    }

    public String getServicesName() {
        return this.getName();
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpEntityType() {
        return httpEntityType;
    }

    public void setHttpEntityType(String httpEntityType) {
        this.httpEntityType = httpEntityType;
    }

    public String getIsAuthName() {
        if (0 == this.isAuth) {
            return "需要";
        }
        return "不需要";
    }

    public Integer getTemplateSts() {
        return templateSts;
    }

    public void setTemplateSts(Integer templateSts) {
        this.templateSts = templateSts;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getDubboVersion() {
        return dubboVersion;
    }

    public void setDubboVersion(String dubboVersion) {
        this.dubboVersion = dubboVersion;
    }

    public String getJsonExample() {
        return jsonExample;
    }

    public void setJsonExample(String jsonExample) {
        this.jsonExample = jsonExample;
    }

    public String getXmlExample() {
        return xmlExample;
    }

    public void setXmlExample(String xmlExample) {
        this.xmlExample = xmlExample;
    }

    public String getServiceNameCn() {
        return serviceNameCn;
    }

    public void setServiceNameCn(String serviceNameCn) {
        this.serviceNameCn = serviceNameCn;
    }

    public String getDubboGroup() {
        return dubboGroup;
    }

    public void setDubboGroup(String dubboGroup) {
        this.dubboGroup = dubboGroup;
    }

    public Integer getDubboInsulate() {
        return dubboInsulate;
    }

    public void setDubboInsulate(Integer dubboInsulate) {
        this.dubboInsulate = dubboInsulate;
    }

    public Integer getDubboInvoke() {
        return dubboInvoke;
    }

    public void setDubboInvoke(Integer dubboInvoke) {
        this.dubboInvoke = dubboInvoke;
    }

    public Integer getManageType() {
        return manageType;
    }

    public void setManageType(Integer manageType) {
        this.manageType = manageType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
