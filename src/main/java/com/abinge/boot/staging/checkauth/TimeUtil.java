package com.abinge.boot.staging.checkauth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static final String FORMAT_YYYYMMDD2 = "yyyy-MM-dd";

    public static final String FORMAT_MMDD = "MM月dd日";

    public static final String ZERO = "0";

    /**
     * 将date格式化为yyyy-MM-dd HH:mm:ss的string
     *
     * @param date
     * @return
     */
    public static String dateToYmdhmsStr(Date date) {
        return toDate(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateToYmdhms(Date date) {
        return toDate(date, FORMAT_YYYYMMDDHHMMSS);
    }
    public static String dateToYmdhm(Date date) {
        return toDate(date, FORMAT_YYYYMMDDHHMM);
    }
    public static String dateToYmd(Date date) {
        return toDate(date, FORMAT_YYYYMMDD);
    }

    /**
     * 将date格式化为yyyy-MM-dd HH:mm:ss的string
     *
     * @param date
     * @return
     */
    public static Date StrToYmdhmsDate(String date) {
        if(StringUtils.isBlank(date)){
            return null;
        }
        return toDate(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    /**
     * 将date格式化为yyyyMMddHHmmss的string
     *
     * @param date
     * @return
     */
    public static Date ymdhmsSToDate(String date) {
        if(StringUtils.isBlank(date)){
            return null;
        }
        return toDate(date, FORMAT_YYYYMMDDHHMMSSSSS);
    }

    /**
     * 根据模板格式化时间,返回string
     *
     * @param dt
     * @param sFmt
     * @return
     */
    public static String toDate(Date dt, String sFmt) {
        if (null == dt || StringUtils.isBlank(sFmt)) {
            return null;
        }
        SimpleDateFormat sdfFrom = null;
        String sRet = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            sRet = sdfFrom.format(dt).toString();
        } catch (Exception ex) {
            logger.error("toDate失败", ex);
            return null;
        } finally {
            sdfFrom = null;
        }
        return sRet;
    }

    /**
     * 根据模板格式化时间,返回date
     *
     * @param dt
     * @param sFmt
     * @return
     */
    public static Date dateToDate(Date dt, String sFmt) {
        if (null == dt || StringUtils.isBlank(sFmt)) {
            return null;
        }
        SimpleDateFormat sdfFrom = null;
        Date sRet = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            String s = sdfFrom.format(dt);
            sRet = toDate(s, sFmt);
        } catch (Exception ex) {
            logger.error("toDate失败", ex);
            return null;
        } finally {
            sdfFrom = null;
        }
        return sRet;
    }

    public static int toDateInt(Date dt, String sFmt) {
        return Integer.parseInt(toDate(dt, sFmt));
    }

    public static Date toDate(String sDate, String sFmt) {
        if (StringUtils.isBlank(sDate) || StringUtils.isBlank(sFmt)) {
            return null;
        }

        SimpleDateFormat sdfFrom = null;
        Date dt = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            dt = sdfFrom.parse(sDate);
        } catch (Exception ex) {
            logger.error("toDate失败", ex);
            return null;
        } finally {
            sdfFrom = null;
        }

        return dt;
    }

    public static Date toDate(Integer intDate, String sFmt) {
        return toDate(String.valueOf(intDate), sFmt);
    }

    public static Date dayBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -14);
        return calendar.getTime();
    }

    /**
     * 在某一天的基础上增加或减少x天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date dayBefore(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static String dayBefore(String sDate, String sFmt, int days) {
        Date date = toDate(sDate, sFmt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return toDate(calendar.getTime(), sFmt);
    }


    public static boolean isValidDate(String str, String sFmt) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(sFmt);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isValidDate(int date) {
        return isValidDate(String.valueOf(date), FORMAT_YYYYMMDD);
    }

    public static long delDate(int startDateId, int endDateId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return (sdf.parse(String.valueOf(startDateId)).getTime() - sdf.parse(String.valueOf(endDateId)).getTime()) / (24 * 60 * 60 * 1000);
    }

    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 2个时间相差几个月
     *
     * @param fromDate
     * @param endDate
     * @return
     */
    public static int getDistanceMonth(Date fromDate, Date endDate) {
        int distance = 0;
        Date tmp = dayBeforeMONTH(fromDate, 1);
        while (tmp.before(endDate) || tmp.equals(endDate)) {
            tmp = dayBeforeMONTH(tmp, 1);
            distance++;
        }
        return distance;
    }

    /**
     * 2个时间相差多个周
     *
     * @param fromDate
     * @param endDate
     * @return
     */
    public static int getDistanceWeek(Date fromDate, Date endDate) {
        int distance = 0;
        Date tmp = dayBeforeWeek(fromDate, 1);
        while (tmp.before(endDate) || tmp.equals(endDate)) {
            tmp = dayBeforeWeek(tmp, 1);
            distance++;
        }
        return distance;
    }

    public static Date dayBeforeMONTH(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, days);
        return calendar.getTime();
    }


    public static Date dayBeforeWeek(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 时间戳转时间
     * @param timeStamp
     * @return
     */
    public static Date timeStampToDate(Long timeStamp) {
        if (null == timeStamp) {
            return null;
        }
        return new Date(timeStamp);
    }
    /**
     * 计算今天距离指定日期过了多少时间
     *
     * @param startTime 开始时间
     * @return 时间差
     */
    public static String calculateTime(String startTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        try {
            d1 = df.parse(startTime);
        } catch (ParseException e) {
            logger.info("计算时间差异常：" + e.getMessage());
        }
        Date d2 = new Date();
        long diff = d2.getTime() - d1.getTime();
        long hours = diff / (1000 * 60 * 60);
        String hour = String.valueOf(hours);
        long minutes = diff / (1000 * 60) - hours * 60;
        String minute = String.valueOf(minutes);
        long seconds = (diff / (1000)) - hours * 3600 - minutes * 60;
        String second = String.valueOf(seconds);
        String log = "耗时";
        if (!ZERO.equals(hour)) {
            log += hour + "H";
        }
        if (!ZERO.equals(minute)) {
            log += minutes + "m";
        }
        if (!ZERO.equals(second)) {
            log += second + "s";
        }
        return log;
    }

    /**
     * @param startTime 开始时间
     * @return 时间差多少天
     */
    public static int calculateDay(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return (int) days;
    }
    /**
     * @param startTime 开始时间
     * @return 时间差多少小时
     */
    public static int calculateHour(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        long days = diff / (1000 * 60 * 60);
        return (int) days;
    }
    /**
     * 获取 yyyy-MM-dd 格式的string
     *
     * @param date
     * @return
     */
    public static String getYmdDateString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdfFrom = null;
        String timeStr = null;
        try {
            sdfFrom = new SimpleDateFormat(FORMAT_YYYYMMDD2);
            timeStr = sdfFrom.format(date);
        } catch (Exception ex) {
            logger.error("toDate失败", ex);
        } finally {
            sdfFrom = null;
        }
        return timeStr;
    }

    /**
     * 获取yyyy-MM-dd 的date
     *
     * @param date
     * @return
     */
    public static Date getYmdDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdfFrom = null;
        Date parseDate = null;
        try {
            sdfFrom = new SimpleDateFormat(FORMAT_YYYYMMDD2);
            String timeStr = sdfFrom.format(date);
            parseDate = sdfFrom.parse(timeStr);
        } catch (Exception ex) {
            logger.error("toDate失败", ex);
        } finally {
            sdfFrom = null;
        }
        return parseDate;
    }

    /**
     * 将时间减一秒（将整点时间转换成前一天的23:59:59）
     *
     * @param date
     * @return
     */
    public static Date getPreDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取N个月之后的日期
     *
     * @param date
     * @param number
     * @return
     */
    public static Date getAfterMonth(Date date, int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, number);//在日历的月份上增加number个月
        return c.getTime();
    }

    /**
     * 获取N天之后的日期
     *
     * @param date
     * @param number
     * @return
     */
    public static Date getAfterDay(Date date, int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(date);//设置日历时间
        c.set(Calendar.DATE, c.get(Calendar.DATE) + number);
        return c.getTime();
    }

    /**
     * 比较俩时间大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    public static String getLastDayOfMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);  //年
        int month = Integer.parseInt(yearMonth.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        // 设置日历中月份的最大天数
        //cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前月第一天
     * @param date
     * @return
     */
    public static Date firstDayCurrent(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取当前月最后一天
     * @param date
     * @return
     */
    public static Date lastDayCurrent(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
