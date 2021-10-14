package com.abinge.boot.staging.checkauth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 子用户API相关信息
 *
 * @author chengjian
 * @date 2020/06/11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountSubApiVO {

    private List<AppSystemParamVO> systemParamList;

    private List<String> whiteList;

    private String rsaPublicKey;

    private String rsaPrivateKey;

    private String signAccessKeyId;

    private String signAccessSecret;

    private String signAlgorithm;
}
