package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author : yfzhangbin
 * @date : 2020/5/27 19:44
 */
@Slf4j
public class DateUtils {

    private final static DateTimeFormatter fmt_yyyyMMdd_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    public static String now() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(fmt_yyyyMMdd_HHmmss);
    }

    /**
     * 日期格式化
     *
     * @param date    日期对象
     * @param pattern 日期格式
     * @return 日期格式化输出
     */
    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化当天日期
     *
     * @param pattern 日期格式
     * @return 当天日期格式化输出
     */
    public static String now(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 格式化昨天日期
     *
     * @param pattern 日期格式
     * @return 昨天日期格式化输出
     */
    public static String yesterday(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        calendar.getTime();
        return format(calendar.getTime(), pattern);
    }

    /**
     * 格式化几天前的日期
     *
     * @param pattern 日期格式
     * @return 日期格式化输出
     */
    public static String lastDay(String pattern, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -day);
        calendar.getTime();
        return format(calendar.getTime(), pattern);
    }



    public static Date getDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR, 8);
        date = calendar.getTime();
        System.out.print(format(date, yyyyMMddHHmmss));
        return date;
    }


    /**
     * 将字符串转换为date类型
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDateByPattern(String str, String pattern) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(str);
        return date;
    }



    /**
     * 功能描述：返回日期
     *
     * @param date 日期
     * @return 返回日期
     */
    public static Date getStrDate(String date, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 获取date1领先date2的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDayDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("parameter date1 or date2 cannot be null");
        }
        return (int)(date1.getTime()-date2.getTime())/(24*60*60*1000);
    }

}
