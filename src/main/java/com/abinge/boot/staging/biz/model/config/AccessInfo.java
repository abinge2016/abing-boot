package com.abinge.boot.staging.biz.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 访问凭证信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessInfo implements Serializable {
    /**
     * 归属code
     */
    private String sourceCode;
    /**
     * appkey
     */
    private String appKey;
    /**
     * appSecret
     */
    private String appSecret;

    /**
     * 算法类型：weiyi_md5
     */
    private String accessAlgorithm;
}
