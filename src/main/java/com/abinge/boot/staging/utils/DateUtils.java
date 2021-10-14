package com.abinge.boot.staging.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final String DATE_FORMAT_YY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final int DEFAULT_DAYS = 30;

    public static String getSimpleDateFormat(Date d) {
        if (null == d) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YY_MM_DD_HH_MM);
        return simpleDateFormat.format(d);
    }

    public static String localDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static boolean before(Date date){
        LocalDate nowTime= LocalDate.now();
        LocalDate endTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return nowTime.isBefore(endTime);
    }

    public static Date add(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DAYS);
        return calendar.getTime();
    }
}