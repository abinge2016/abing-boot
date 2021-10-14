package com.abinge.boot.staging.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

public class AESUtil {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET = "hvxtgjBKJviq2Mgb";


    private static Key getSecretKey(String secretKey) {
        return new SecretKeySpec(secretKey.getBytes(CHARSET), KEY_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param originData
     * @return
     */
    public static String encrypt(String originData) throws Exception {
        String ret = null;
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        byte[] dataBytes = originData.getBytes(CHARSET);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRET));
        byte[] enBytes = cipher.doFinal(dataBytes);
        enBytes = Base64.encodeBase64(enBytes);
        ret = new String(enBytes, CHARSET);
        return ret;
    }
}
