package com.common;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: gina
 * @Date: 2023/10/30 10:54
 * @Description:
 */
@Slf4j
public class DateUtil {

    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY_MILLI_SECONDS = 1L * 24 * 60 * 60 * 1000;

    /**
     * 一小时的毫秒数
     */
    public static final long ONE_HOUR_MILLI_SECONDS = 1L * 60 * 60 * 1000;

    /**
     * 一分钟的毫秒数
     */
    public static final long ONE_MINUTE_MILLI_SECONDS = 1L * 60 * 1000;


    /**
     * 日期格式 yyyy/MM/dd HH:mm:SS
     */
    public static final String COMMON_FORMAT = "yyyy-MM-dd HH:mm:SS";

    /**
     * 日期格式 yyyyMMdd
     */
    public static final String FORMAT_YEAR_MM_DD = "yyyy-MM-01 00:00:00";
    /**
     * 库表修改时间格式化
     */
    public static final String MODIFY_TIME_FORMAT = "yyyy-01-01 00:00:00";


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStampToDate(Date seconds, String format) {
        if (seconds == null) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(seconds);
    }


    /**
     * 获取字符串时间
     *
     * @return 返回短时间字符串格式yyyyMMdd
     */
    public static String getStringDateByFmt(Date date, String fmt) {
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        return formatter.format(date);
    }


    /**
     * 指定月份得第一天
     *
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(calendar.getTime());
    }

    /**
     * 指定月份得最后一天
     *
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        int lastDay = 0;
        if (month == 2) {
            lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        } else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(calendar.getTime());
    }


    public static Date genGmtDate(String format) {
        String b = DateUtil.getStringDateByFmt(new Date(), format);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        try {
            return dateFormat.parse(b);
        } catch (ParseException e) {
            log.error("new RuntimeException({}),{}", e.getMessage(), e);
        }
        return new Date();
    }


    /*获取当前年月：20231211*/
    public static Integer getYearAndMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1; // 因为Calendar类中月份从0开始计数，所以需要加1
        int year = calendar.get(Calendar.YEAR);
        return Integer.valueOf(year + "" + month);
    }


    /**
     * 获取上个月的年月
     *
     * @return
     */
    public static Integer getPreMonth() {
        // 创建 Calendar 对象并设置为当前日期和时间
        Calendar calendar = Calendar.getInstance();

        // 将日期调整到上个月
        calendar.add(Calendar.MONTH, -1);

        // 输出上个月的年、月、日信息
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 注意月份从0开始计数，所以需要加1
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return Integer.parseInt(year + "" + month);
    }

    private static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getNowStr(){
        LocalDateTime now = LocalDateTime.now();
        return pattern.format(now);
    }
}
