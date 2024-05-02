package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ProcessUtils {

    /**
     * 精确到秒的日期格式
     */
    public final static String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 调用此方法时，默认返回值为0
     * @param origin 要进行转型的对象
     * @return 转型后的int值
     */
    public static int parseInt(Object origin) {
        return parseInt(origin, 0);
    }

    /**
     * 默认返回值为入参defaultValue
     * @param origin 要进行转型的对象
     * @param defaultValue 默认值，有异常情况时返回的值
     * @return 转型后的int值
     */
    public static int parseInt(Object origin, int defaultValue) {
        int result = defaultValue;
        if (origin == null) {
            return result;
        }

        String s = origin.toString();
        if (isStrBlank(s)) {
            return result;
        }
        try {
            result = Integer.parseInt(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + origin, e);
        }
        return result;
    }

    /**
     * 调用此方法时，默认返回值为""
     * @param o 要进行转型的对象
     * @return 转型后的String值
     */
    public static String toString(Object o) {
        return toString(o , "").trim();
    }

    /**
     * 默认返回值为入参defaultValue
     * @param origin 要进行转型的对象
     * @param defaultValue 默认返回值
     * @return 转型后的String值
     */
    public static String toString(Object origin,String defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        String temp = origin.toString();
        if (ProcessUtils.isStrNotBlank(temp)){
            return temp;
        }
        return defaultValue;
    }

    public static String toStringTrim(Object o) {
        return toString(o).trim();
    }
    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return date string
     */
    public static String formatDate(Date date, String format) {
        try {
            return null != date ? new SimpleDateFormat(null == format ? SECOND_FORMAT : format).format(date) : null;
        } catch (Exception e) {
            log.error("Exception encountered when formating date: " + e.getMessage(), e);
        }

        return null;
    }

    /**
     * 获取标准时间对应的秒
     * @param time
     * @return
     */
    public static long getTimeSecond(String time) {
        if (isStrEmpty(time)) {
            return 0;
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat(SECOND_FORMAT);
        try {
            long timeLong = timeFormat.parse(time).getTime()/1000;
            return timeLong;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     *
     * Description About getStringTimeFromLongTime: <br>
     * 将long类型的秒数时间，转换为String类型的格式，例如：2015-01-29 19:52:00
     * @param time 从1970年开始的秒数
     * @return 返回时间的String格式，例如：2015-01-29 19:52:00
     */
    public static String getStringTimeFromLongTime(long time){
        String result = "";
        try {
            Date date = new Date(time*1000);
            result =  new SimpleDateFormat(SECOND_FORMAT).format(date);
        } catch (Exception e) {
            return "";
        }
        return result;
    }

    public static boolean isCollectionEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof Map && ((Map) o).isEmpty()) {
            return true;
        } else if (o instanceof List && (((List) o).isEmpty())) {
            return true;
        }
        return false;
    }

    public static boolean isCollectionNotEmpty(Object o) {
        return !isCollectionEmpty(o);
    }

    public static boolean isStrNotEmpty(final CharSequence cs) {
        return !isStrEmpty(cs);
    }

    public static boolean isStrEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isStrNotBlank(final CharSequence cs) {
        return !isStrBlank(cs);
    }

    public static boolean isStrBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static double parseDouble(Object origin) {
        return parseDouble(origin, 0.0);
    }

    public static double parseDouble(Object origin, double defaultValue) {
        double result = defaultValue;
        if (origin == null) {
            return result;
        }
        String s = origin.toString();
        if (isStrBlank(s)) {
            return result;
        }
        try {
            result = Double.parseDouble(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + origin, e);
        }
        return result;
    }

    /**
     * 默认返回值为入参defaultValue
     *
     * @param origin       要进行转型的对象
     * @param defaultValue 默认值，有异常情况时返回的值
     * @return 转型后的int值
     * @author changliang
     */
    public static long parseLong(Object origin, long defaultValue) {
        long result = defaultValue;
        if (origin == null) {
            return result;
        }

        String s = origin.toString();
        if (isStrBlank(s)) {
            return result;
        }
        try {
            result = Long.parseLong(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + origin, e);
        }
        return result;
    }

    /**
     * 调用此方法时，默认返回值为0
     *
     * @param origin 要进行转型的对象
     * @return 转型后的int值
     * @author changliang
     */
    public static long parseLong(Object origin) {
        return parseLong(origin, 0);
    }

    //下划线转驼峰
    public static String underline2Camel(String underline){
        Pattern pattern = Pattern.compile("[_]\\w");
        Matcher matcher = pattern.matcher(underline);
        while(matcher.find()){
            String w = matcher.group().trim();
            underline = underline.replace(w,w.toUpperCase().replace("_", ""));
        }
        return underline;
    }


    private static Pattern humpPattern = Pattern.compile("[A-Z]");


    // 驼峰转下划线
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
