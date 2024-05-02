package io.geekidea.springboot.assembly.demo.utils;

import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jinmengyong3
 * @description:
 * @date 2021/4/26 21:17
 */
@Slf4j
public class DateUtil {

    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 初始化ThreadLocal数据
     */
    private static final ThreadLocal<Map<String, SimpleDateFormat>> sdfMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<>();
        }
    };
    /**
     * 剔除日期后的 .0
     * 例如：2019-07-02 16:07:30.0 -> 2019-07-02 16:07:30
     *
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

    public static String format(Date date, DatePatternEnum datePatternEnum) {
        if (null == date){
            return null;
        }
        return getLocalSdf(datePatternEnum.pattern).format(date);
    }

    /**
     * 根据pattern(yyyy-MM-dd)获取simpleDateFormat
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getLocalSdf(String pattern) {
        Map<String, SimpleDateFormat> formatMap = sdfMap.get();
        SimpleDateFormat simpleDateFormat = formatMap.get(pattern);

        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern);
            formatMap.put(pattern, simpleDateFormat);
        }
        return simpleDateFormat;
    }

    /**
     * 日期格式化
     *
     * @param date    日期(字符串类型)
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
     * 根据pattern(yyyy-MM-dd)将String转为Date
     *
     * @param dateStr
     * @param datePatternEnum
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, DatePatternEnum datePatternEnum) {
        try {
            if (org.apache.commons.lang3.StringUtils.isEmpty(dateStr)){
                return new Date();
            }
            return getLocalSdf(datePatternEnum.pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 日期格式化
     *
     * @param date    日期(字符串类型)
     * @param pattern 日期格式
     * @return 日期对象输出
     */
    public static String parseByPattern(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(sdf.parse(date));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据patter类型，获取上个时间周期，可以返回天、周、月、年，根据选择的type与num而定
     *
     * @param date    传入的日期字符串
     * @param pattern 日期格式
     * @param type    类型： date week month  year
     * @param num     数值：+N  往后推N个周期 ；-N 往前推N个周期
     * @return
     */
    public static String getLastYearOrMonthOrDate(String date, String pattern, String type, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            //此时cal是结束时间
            if ("date".equals(type)) {
                cal.add(Calendar.DATE, num);
            }
            if ("week".equals(type)) {
                cal.add(Calendar.WEEK_OF_YEAR, num);
            }
            if ("month".equals(type)) {
                cal.add(Calendar.MONTH, num);
            }
            if ("year".equals(type)) {
                cal.add(Calendar.YEAR, num);
            }
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 格式化当天日期
     *
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
     * 根据pattern 获取两个日期之间的所有日期列表，包含start,包含end
     */
    public static List<String> getDateListWithPattern(String start, String end, String type, String pattern) throws ParseException {
        List<String> list = new LinkedList<>();
        if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar calendar = Calendar.getInstance();
            Calendar calendarEND = Calendar.getInstance();
            calendar.setTime(sdf.parse(start));
            calendarEND.setTime(sdf.parse(end));
            while (calendar.before(calendarEND) || calendar.equals(calendarEND)) {
                list.add(sdf.format(calendar.getTime()));
                if ("month".equals(type)) {
                    calendar.add(Calendar.MONTH, 1);
                } else if ("year".equals(type)) {
                    calendar.add(Calendar.YEAR, 1);
                } else {
                    calendar.add(Calendar.DATE, 1);
                }

            }
        }
        return list;
    }

    public static List<String> getMonthBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.MONTH, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
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
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取某个日期前后推移后的过期时间，过期时间可以控制到23点59分59秒
     */
    public static String getExpireDate(Date date, int day, Boolean hhmmss) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        if (hhmmss) {
            return getDateAfterCal(str, day) + "23:59:59";
        } else {
            return getDateAfterCal(str, day);
        }

    }

    /**
     * 根据当前日期获取去年同时期日期
     *
     * @param date
     * @return
     */
    public static String getLastYearDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            //此时cal是结束时间
            cal.add(Calendar.YEAR, -1);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
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
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 按照某个pattern格式 获取某个日期前后推移后的时间(按月)
     *
     * @param date
     * @param month
     * @param pattern
     * @return
     */
    public static String getDateForMonthAfterCalWithPattern(String date, int month, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            //此时cal是结束时间
            cal.add(Calendar.MONTH, month);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
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

    /**
     * 判断当前字符串是不是有效日期，日期格式yyyy-MM-dd
     *
     * @param str 日期字符串
     * @return true有效日期，false无效日期
     */
    public static boolean isDate(String str) {
        if (str != null) {
            try {
                LocalDate date = LocalDate.parse(str, DateTimeFormat.forPattern("yyyy-MM-dd"));
                return date != null;
            } catch (Exception e) {
                log.warn("无效日期{}输入", str, e);
            }
        }
        return false;
    }

    /**
     * 返回几个月后的日期，在当前日期上添加指定月数，然后返回新的日期；
     *
     * @param month 指定月份
     * @return 加上指定月数后的日期
     */
    public static Date getDateAfterPlusMonths(int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }


    /**
     * 获取当前日期在一年中的第几周  返回格式类似：202219
     *
     * @param dt
     * @return
     */
    public static String getWeekOfYear(String dt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        // 在四天以上才算入下一周，否则为上一个周
        calendar.setMinimalDaysInFirstWeek(4);
        // 设置一周的开始为周几，设置为周一
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String res = "";
        //如果是跨年，尤其是xxxx-01-01情况，如果周大于52，应该是上一年的；否则，是这一年的
        if (week >= 52 && month == 1) {
            res = (year - 1) + "" + week;
        } else {
            res = year + "" + (week < 10 ? "0" + week : week);
        }
        return res;
    }




    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static String getQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6) c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9) c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12) c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            dt = shortSdf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获取当天日期的上一周开始结束时间（年月日:"yyy-MM-dd"） --王楠
     */
    public static String getBeginAndEndTimeOfLastWeek(String strDate, String pattern) throws ParseException {
        StringBuilder res = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(strDate));
        //将每周第一天设为星期一，默认是星期天
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONTH);
        res.append(df.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        res.append(",").append(df.format(cal.getTime()));
        return res.toString();
    }

    /**
     * 获取当天日期的上一周开始结束时间（年月日:"yyy-MM-dd"）
     */
    public static String getCurrentWeek1(String strDate, String pattern) throws ParseException {
        StringBuilder res = new StringBuilder();
        // 设定时间值
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(strDate));
        // 如果是周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        // 获取当前日期是当周的第i天
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        // 获取当前日期所在周的第一天
        calendar.add(Calendar.DATE, -i + 1);
        res.append(sdf.format(calendar.getTime()));
        // 获取当前日期所在周的最后一天
        calendar.add(Calendar.DATE, 6);
        res.append(",").append(sdf.format(calendar.getTime()));
        return res.toString();
    }


    public static String getCurrentWeek(String date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setTimeInMillis(df.parse(date).getTime());//当前时间

        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日

        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        String s1 = df.format(cld.getTime());
        System.out.println(df.format(cld.getTime()));

        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
        String s7 = df.format(cld.getTime());

        System.out.println(df.format(cld.getTime()));
//        return Arrays.asList(s1,s7).  Stringu
        return org.apache.commons.lang3.StringUtils.join(Arrays.asList(s1, s7));
    }


    /**
     * 获取当前日期上一个月 开始时间
     *
     * @return
     */
    public static String getPreStartMonth(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(sdf.parse(date));
        int month = startCalendar.get(Calendar.MONTH);
        startCalendar.set(Calendar.MONTH, month - 1);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(startCalendar.getTime());
    }

    /**
     * 获取当前日期上一个月 结束时间
     * 当前日期为31号存在bug已修复
     *
     * @return
     */
    public static String getPreEndMonth(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(sdf.parse(date));
        int month = endCalendar.get(Calendar.MONTH);
        endCalendar.set(Calendar.MONTH, month - 1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(endCalendar.getTime());

    }


    /**
     * 获取当前日期上一季度 开始时间
     *
     * @return
     */
    public static String getPreStartQuarter(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(sdf.parse(date));
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(startCalendar.getTime());
    }

    /**
     * 获取当前日期上一季度 结束时间
     * 当前日期为31号存在bug已修复
     *
     * @return
     */
    public static String getPreEndQuarter(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(sdf.parse(date));
        int month = endCalendar.get(Calendar.MONTH);
        endCalendar.set(Calendar.MONTH, (month / 3 - 1) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(endCalendar.getTime());

    }

    /**
     * 获取当前日期上个半年 开始时间
     *
     * @return
     */
    public static String getPreStartHalfYear(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(sdf.parse(date));
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 6 - 1) * 6);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(startCalendar.getTime());
    }

    /**
     * 获取当前日期上个半年 结束时间
     *
     * @return
     */
    public static String getPreEndHalfYear(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(sdf.parse(date));
        int month = endCalendar.get(Calendar.MONTH);
        endCalendar.set(Calendar.MONTH, (month / 6 - 1) * 6 + 5);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(endCalendar.getTime());
    }
    //========================获取yyyy-MM格式的月


    /**
     * 获取上个月月份
     *
     * @return
     */
    public static final String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        // 设置为上一个月
        //calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * 当前日期往后加N小时
     * 日期格式(yyyy-MM-dd HH:mm:ss : 2023-02-23 12:00:22)
     * @param currentDay 当前时间 date
     * @param num  小时数
     * @return 结果天数
     */
    public static Date addNHour(Date currentDay, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        calendar.add(Calendar.HOUR_OF_DAY, num);
        return calendar.getTime();
    }
}
