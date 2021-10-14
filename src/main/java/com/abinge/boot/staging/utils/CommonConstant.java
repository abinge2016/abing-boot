package com.abinge.boot.staging.utils;

import java.nio.charset.Charset;

public class CommonConstant {
    private CommonConstant() {

    }

    //编码格式：utf-8
    public static final Charset CHARSET = Charset.forName("UTF-8");

    //签名摘要算法MD5
    public static final String SIGNATURE_ALGORITHM_MD5 = "md5";

    //签名摘要算法sha256
    public static final String SIGNATURE_ALGORITHM_SHA256 = "sha256";

    //加密算法：rsa
    public static final String ALGORITHM_TYPE_ENCRYPT_RSA = "rsa";

    //加密算法：aes
    public static final String ALGORITHM_TYPE_ENCRYPT_AES = "aes";

    //HTTP方法：post
    public static final String HTTP_METHOD_POST = "post";

    //开放平台的sourceId
    public static final int FROG_SOURCEID = 148;

    /**
     * 保存公钥的前置字符串
     */
    public static final String PUBLICENCRYPTKEY = "PublicEncryptKey";

    /**
     * 保存私钥的前置字符串
     */
    public static final String PRIVATEENCRYPTKEY = "PrivateEncryptKey";

    /**
     * dubbo应用名称
     */
    public static final String APPLICATIONNAME = "frog-service";
}