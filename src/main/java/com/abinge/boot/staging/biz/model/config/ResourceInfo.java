package com.abinge.boot.staging.biz.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 资源信息
 * @author chenbj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceInfo implements Serializable {

    /**
     * 应用code
     */
    private String appCode;
    /**
     * 资源code
     */
    private String resourceCode;

    /**
     * 资源值
     */
    private String resourceValue;

    /**
     * 资源类型：1.url
     */
    private Integer resourceType;
}
