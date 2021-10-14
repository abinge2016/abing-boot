package com.abinge.boot.staging.utils;

import com.abinge.boot.staging.exceptions.BizException;
import com.google.common.collect.Maps;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class MockLoginUtil {



    public static void mockLogin(String url, String loginId, String password, CookieStore httpCookieStore) throws Exception {
        Map<String, String> loginFormData = buildLoginFormData(loginId, password);
        HttpClientUtil.doPostFormAndGetCookies(url, loginFormData, ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), httpCookieStore);
        List<Cookie> cookies = httpCookieStore.getCookies();
        if (CollectionUtils.isEmpty(cookies) || cookies.size() <= 1) {
            throw new BizException("登录失败");
        }
    }

    private static Map<String, String> buildLoginFormData(String loginId, String pwd) throws Exception {
        Map<String, String> formData = Maps.newHashMap();
        formData.put("method", "dologin");
        formData.put("target", "");
        formData.put("validCodeType", "");
        formData.put("type", "login");
        formData.put("loginId", loginId);
        formData.put("encryptpwd", encryptPwd(pwd));
        return formData;
    }

    private static String encryptPwd(String pwd) throws Exception {
        String encrypt = AESUtil.encrypt(pwd);
        return encrypt;
    }
}