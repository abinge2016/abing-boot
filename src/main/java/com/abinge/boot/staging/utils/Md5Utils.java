package com.abinge.boot.staging.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    public static String encryptMD5(String data) throws NoSuchAlgorithmException {
        byte[] md5Byte = encryptMD5(data.getBytes(CommonConstant.CHARSET));
        return byte2hex(md5Byte);
    }

    /**
     * md5加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    /**
     * 二进制转换为大写的十六进制
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}