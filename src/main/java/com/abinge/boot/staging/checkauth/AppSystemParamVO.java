package com.abinge.boot.staging.checkauth;

import lombok.Data;

/**
 * 应用数据权限配置（即应用系统参数配置）
 *
 * @author chengjian
 * @date 2020/06/11
 */
@Data
public class AppSystemParamVO {


    private String systemParamKey;

    private String systemParamValue;
}
