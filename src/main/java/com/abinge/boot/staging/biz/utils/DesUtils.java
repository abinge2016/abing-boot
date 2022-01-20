package com.abinge.boot.staging.biz.utils;

import com.guahao.opensaas.gateway.biz.constants.ServicePipelineCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Slf4j
public class DesUtils {
    /**
     * DES 加密
     *
     * @param originData 原始数据,未加密
     * @param secret     加密秘钥
     * @return
     */
    public static String encrypt(String originData, String secret) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(secret.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, random);
            byte[] bytes = cipher.doFinal(originData.getBytes());
            return new String(Base64.encodeBase64URLSafe(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("DES加密异常:", e);
            return "";
        }

    }

    /**
     * DES 解密
     *
     * @param encryptData 加密串
     * @param secret      加密/解密 secret
     * @return
     */
    public static String decrypt(String encryptData, String secret) {
        try {
            // --通过base64,将字符串转成byte数组
            byte[] bytesrc = Base64.decodeBase64(encryptData.getBytes());
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(secret.getBytes(StandardCharsets.UTF_8));
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey secretkey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretkey, random);
            // 真正开始解密操作
            byte[] bytes = cipher.doFinal(bytesrc);
            return new String(bytes);
        } catch (Exception e) {
            log.error("DES解密异常:", e);
            com.guahao.opensaas.gateway.biz.utils.ParamCheckUtil.buildFail(ServicePipelineCode.API_PARAMETER_DECRYPT_ERROR);
        }
        return StringUtils.EMPTY;

    }


}
