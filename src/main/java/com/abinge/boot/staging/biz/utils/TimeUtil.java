package com.abinge.boot.staging.biz.utils;


import com.abinge.boot.staging.biz.constants.GlobalConstants;

import java.time.Instant;

/**
 * 时间处理工具
 * @author chenbj
 */
public class TimeUtil {
    public static boolean checkTimestampTimeout(Long timestamps){
        long now = Instant.now().toEpochMilli();
        return Math.abs(now - timestamps) > GlobalConstants.MAX_MILLIS;
    }
}
