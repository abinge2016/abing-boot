package com.abinge.boot.staging.biz.constants;

import java.util.Arrays;
import java.util.List;

public class SysHeadersContext {
    /**
     * 应用账号
     */
    public static final String APP_KEY = "appkey";

    /**
     * sign
     */
    public static final String SIGN = "sign";

    /**
     * 请求消息ID
     */
    public static final String MESSAGE_ID = "message-id";

    public static final String METHOD = "method";

    /**
     * 时间戳
     */
    public static final String TIMESTAMP = "timestamp";

    public static final String VERSION = "version";

    public static final String CONTENT_MD5 = "content-md5";

    public static final List<String> OPENAPI_SIGN_HEAD_LIST = Arrays.asList(APP_KEY, TIMESTAMP, METHOD, VERSION, MESSAGE_ID, CONTENT_MD5);
}