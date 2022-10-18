package io.geekidea.springboot.assembly.demo.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jinmengyong3
 * @description:
 * @date 2021/4/26 21:17
 */
public class DateUtilss {
    /**
     * 剔除日期后的 .0
     * 例如：2019-07-02 16:07:30.0 -> 2019-07-02 16:07:30
     * @return
     */
    public static String trimSuffix(String date) {
        if (date != null) {
            int suffixIndex = date.lastIndexOf(".0");
            if (suffixIndex > 0) {
                return date.substring(0, suffixIndex);
            }
        }
        return date;
    }

    /**
     * 日期格式化
     * @param date 日期对象
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
     * 日期格式化
     * @param date 日期(字符串类型)
     * @param pattern 日期格式
     * @return 日期对象输出
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化当天日期
     * @param pattern 日期格式
     * @return 当天日期格式化输出
     */
    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 通过给定年月获取对应的当月月开始和当月结束时间（yyyy-MM-dd格式）
     *
     * @param
     * @return
     */
    public static List<String> getFormatTime(int year, int month) {
        List<String> times = new ArrayList<>();
        int days = getMonthDays(year, month);
        if (month < 10) {
            times.add(year + "-0" + month + "-" + "01");
            times.add(year + "-0" + month + "-" + days);
        } else {
            times.add(year + "-" + month + "-" + "01");
            times.add(year + "-" + month + "-" + days);
        }
        return times;

    }
    /**
     * 获取某年某月有多少天
     */
    public static int getMonthDays(int year, int month) {
        int days = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else if (month == 2) {
            //判断是否是闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }
        return days;
    }

    /**
     * 获取当前月份的上个月的开始和结束时间
     *
     * @param time
     * @throws Exception
     */
    public static List<String> getMonthTime(String time) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> times = new ArrayList<>();
        Calendar c = getPreMonth(time);
        //获取某月最小天数
        int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        c.set(Calendar.DAY_OF_MONTH, firstDay);
        // 上个月第一天
        String startTime = format.format(c.getTime());
        Calendar c2 = getPreMonth(time);
        int lastDay = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        c2.set(Calendar.DAY_OF_MONTH, lastDay);
        String endTime = format.format(c2.getTime());
        times.add(startTime);
        times.add(endTime);
        return times;

    }

    /**
     * 从当前时间"yyyy-MM-dd"格式获取上个月的时间
     */
    public static Calendar getPreMonth(String startDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = format.parse(startDate);
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c;
    }
    /**
     * 获取两个日期之间相差天数
     */
    public static long getBetweenDays(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(start));
        long timeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(sdf.parse(end));
        long timeInMillis2 = calendar.getTimeInMillis();
        long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
        return betweenDays;
    }
    /**
     * 获取两个日期之间的所有日期列表，包含start,包含end
     */
    public static List<String> getDateList(String start, String end) throws ParseException {
        List<String> list = new LinkedList<>();
        if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Calendar calendarEND = Calendar.getInstance();
            calendar.setTime(sdf.parse(start));
            calendarEND.setTime(sdf.parse(end));
            while (calendar.before(calendarEND) || calendar.equals(calendarEND)) {
                list.add(sdf.format(calendar.getTime()));
                calendar.add(Calendar.DATE, 1);
            }
        }
        return list;
    }
    /**
     * 获取某个日期前后推移后的时间
     */
    public static String getDateAfterCal(String date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            //此时cal是结束时间
            cal.add(Calendar.DATE, day);
            return sdf.format(cal.getTime());
        }catch (ParseException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取某个日期前后推移后的时间(按月)
     */
    public static String getDateForMonthAfterCal(String date, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            //此时cal是结束时间
            cal.add(Calendar.MONTH, month);
            return sdf.format(cal.getTime());
        }catch (ParseException e){
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 将日期由Date类型转为String
     */
    public static String getDate(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.format(date);
        }
        return "";
    }
}
