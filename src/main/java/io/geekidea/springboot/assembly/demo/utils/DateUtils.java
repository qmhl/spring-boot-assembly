package io.geekidea.springboot.assembly.demo.utils;


import com.alibaba.excel.util.StringUtils;
import io.geekidea.springboot.assembly.demo.constant.QueryCycleEnum;
import io.geekidea.springboot.assembly.demo.constant.TimeDimEnum;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
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
    public static final String yyyyMMddHHmmssNew = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmssSNew = "yyyy-MM-dd HH:mm:ss.S";
    public static final String yyyyMMddNew = "yyyy/MM/dd";

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

    /**
     * 返回两个日期之间的所有日期
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getDays(String startTime, String endTime, String indexName) {
        log.info("getDays 入参是：startTime={}，endTime={}，indexName={}", startTime, endTime, indexName);
        if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
            throw new RuntimeException(">>>startTime or  endTime is empty<<<");
        }


        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(indexName + dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DATE, 1);
            }

        } catch (ParseException e) {
            log.error("getDays 出现异常：", e);
        }

        return days;
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
     *
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
     * 功能描述：返回String日期
     *
     * @param date 日期
     * @return 返回日期
     */
    public static String getStrDateByString(String date, String patternDate, String patternString) throws Exception {
        SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
        SimpleDateFormat sdfString = new SimpleDateFormat(patternString);
        return sdfString.format(sdfDate.parse(date));
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
        return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
    }


    /**
     * 将字符串日期转换为需要的格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(String date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            Date d = formatter.parse(date);
            return formatter.format(d);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据当前时间计算周几 的方法
     * 抽成方法 可以复用
     * 传入 时间时间类型字符串就可
     * 返回 传入时间为周几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmssNew);
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"}; //可以其他的表现形式
        Calendar cal = Calendar.getInstance();// 获取指定时间
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * 获取两个日期之间的所有年月日
     *
     * @param startDate
     * @param endDate
     * @return：YYYY-MM
     */
    public static List<String> getDayBetweenDate(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        while (startDate.getTime() <= endDate.getTime()) {
            // 把日期添加到集合
            list.add(sdf.format(startDate));
            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // 获取增加后的日期
            startDate = calendar.getTime();
        }
        return list;
    }

    /**
     * 根据时间粒度和日期获取当前日期为某年的第几周/某年的第几月
     * <p>
     * 时间粒度参考 {@link TimeDimEnum}
     * 日期格式为：2022-01-01
     * 例如：date 为 2022-01-01，type 为 1，返回值为 202152，代表入参的日期为2021年的第52周
     * date 为 2022-01-01，type 为 2，返回值为 2021-01，代表入参的日期为2021年的第1个月（直接截取）
     *
     * @param date
     * @param type
     * @return
     */


    /**
     * 根据某天（某周第一天/某月第一天）获取上周的第一天/上月第一天
     * <p>
     * 时间粒度参考 {@link }
     * 日期格式为：2022-01-01
     *
     * @param date
     * @param type
     * @return
     */
    public static String getFirstDayOfWeekOrMonth(String date, Integer type) {
        if (StringUtils.isBlank(date) || type == null) {
            return null;
        }
        LocalDate initDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(yyyyMMdd));
        if (type.equals(TimeDimEnum.WEEK.getValue())) {
            // 当前周减1
            LocalDate lastWeek = initDate.minusWeeks(1);
            // 获取当前周的第一天
            return lastWeek.format(DateTimeFormatter.ofPattern(yyyyMMdd));
        } else if (type.equals(TimeDimEnum.MONTH.getValue())) {
            // 当前月份减1
            LocalDate lastMonth = initDate.minusMonths(1);
            // 获取当前月的第一天
            LocalDate firstDay = lastMonth.with(TemporalAdjusters.firstDayOfMonth());
            return firstDay.format(DateTimeFormatter.ofPattern(yyyyMMdd));
        }
        return null;
    }

    /**
     * 获取上周和上上周时间
     * @return
     */
    public static Map<String, Date> getLastWeek() {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar cal = Calendar.getInstance();
        int n = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (n == 0) {
            n = 7;
        }

        cal.add(Calendar.DATE, -(14 + (n - 1)));// 上周一的日期
        Date mondayBefore = cal.getTime();
        map.put("mondayBefore", mondayBefore);

        cal.add(Calendar.DATE, 1);
        Date tuesdayBefore = cal.getTime();
        map.put("tuesdayBefore", tuesdayBefore);

        cal.add(Calendar.DATE, 1);
        Date wednesdayBefore = cal.getTime();
        map.put("wednesdayBefore", wednesdayBefore);

        cal.add(Calendar.DATE, 1);
        Date thursdayBefore = cal.getTime();
        map.put("thursdayBefore", thursdayBefore);

        cal.add(Calendar.DATE, 1);
        Date fridayBefore = cal.getTime();
        map.put("fridayBefore", fridayBefore);

        cal.add(Calendar.DATE, 1);
        Date saturdayBefore = cal.getTime();
        map.put("saturdayBefore", saturdayBefore);

        cal.add(Calendar.DATE, 1);
        Date sundayBefore = cal.getTime();
        map.put("sundayBefore", sundayBefore);

        cal.add(Calendar.DATE, 1);// 上周一的日期
        Date monday = cal.getTime();
        map.put("monday", monday);

        cal.add(Calendar.DATE, 1);
        Date tuesday = cal.getTime();
        map.put("tuesday", tuesday);

        cal.add(Calendar.DATE, 1);
        Date wednesday = cal.getTime();
        map.put("wednesday", wednesday);

        cal.add(Calendar.DATE, 1);
        Date thursday = cal.getTime();
        map.put("thursday", thursday);

        cal.add(Calendar.DATE, 1);
        Date friday = cal.getTime();
        map.put("friday", friday);

        cal.add(Calendar.DATE, 1);
        Date saturday = cal.getTime();
        map.put("saturday", saturday);

        cal.add(Calendar.DATE, 1);
        Date sunday = cal.getTime();
        map.put("sunday", sunday);
        return map;
    }

    /**
     * 传入年月日返回7天后年月日
     * @param d
     * @return
     */
    public static String getDateAfter7(String d) throws ParseException {
        // 获取结束时间7天后日期
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(d);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        Date day7 = cal.getTime();
        String endDateAfter7 = ft.format(day7);
        return endDateAfter7;
    }

    /**
     * 获取距明天0点还有几分钟
     * @return 分钟数
     */
    public static int getMinuesUntilTomorrow() {
        long now = System.currentTimeMillis();
        long tomorrow = LocalDate.now().plusDays(1).atStartOfDay().toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
        return (int) (tomorrow - now) / 60_000;
    }

    /**
     * 获得当天最大时间
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
                ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取上1年月、3年月、6年月
     * @return
     */
    public static String getYearMonthPre(Integer type) {
        String date = "";
        if(type.toString().equals(QueryCycleEnum.MONTH.getKey().toString())) {
            return getYearMonthPreStr(-1,"yyyy-MM",false);
        }else if(type.toString().equals(QueryCycleEnum.QUARTER.getKey().toString())) {
            for(int i=-3;i<0;i++) {
                date = date + getYearMonthPreStr(i,"yyyy-MM",false) + ",";
            }
        }else if(type.toString().equals(QueryCycleEnum.HALFYEAR.getKey().toString())) {
            for(int i=-6;i<0;i++) {
                date = date + getYearMonthPreStr(i,"yyyy-MM",false) + ",";
            }
        }
        date = date.substring(0,date.length()-1);
        return date;
    }

    /**
     * 获取年月（自定义）
     * @return
     */
    public static String getYearMonthOnlyRange(String analyseCycle) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy年MM月");
        String [] strs = analyseCycle.split(",");
        String date = "";
        for(String str : strs) {
            Date d = sf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            date = date + sf1.format(calendar.getTime()) + "，";
        }
        date = date.substring(0,date.length()-1);
        return date;
    }

    /**
     * 获取上1年月（范围）、获取上3年月（范围）、获取上6年月（范围）
     * @return
     */
    public static String getYearMonthRange(Integer type) throws ParseException {
        String date = "";
        if(type.toString().equals(QueryCycleEnum.MONTH.getKey().toString())) {
            date = getYearMonthPreStr(-1,"yyyy年MM月",false);
        }else if(type.toString().equals(QueryCycleEnum.QUARTER.getKey().toString())) {
            date = getYearMonthPreStr(-3,"yyyy年MM月",false) + " - " + getYearMonthPreStr(-1,"yyyy年MM月",false);
        }else if(type.toString().equals(QueryCycleEnum.HALFYEAR.getKey().toString())) {
            date = getYearMonthPreStr(-6,"yyyy年MM月",false) + " - " + getYearMonthPreStr(-1,"yyyy年MM月",false);
        }
        return date;
    }

    /**
     * 获取年月（自定义）同比
     * @return
     */
    public static String getYearMonthOnlyRangeTongbi(String analyseCycle) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy年MM月");
        String [] strs = analyseCycle.split(",");
        String date = "";
        for(String str : strs) {
            Date d = sf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.YEAR, -1);
            date = date + sf1.format(calendar.getTime()) + "，";
        }
        date = date.substring(0,date.length()-1);
        return date;
    }

    /**
     * 获取上1年月（范围）同比、获取上3年月（范围）同比、获取上6年月（范围）同比
     * @return
     */
    public static String getYearMonthRangeTongbi(Integer type) throws ParseException {
        String date = "";
        if(type.toString().equals(QueryCycleEnum.MONTH.getKey().toString())) {
            date = getYearMonthPreStr(-1,"yyyy年MM月",true);
        }else if(type.toString().equals(QueryCycleEnum.QUARTER.getKey().toString())) {
            date = getYearMonthPreStr(-3,"yyyy年MM月",true) + " - " + getYearMonthPreStr(-1,"yyyy年MM月",true);
        }else if(type.toString().equals(QueryCycleEnum.HALFYEAR.getKey().toString())) {
            date = getYearMonthPreStr(-6,"yyyy年MM月",true) + " - " + getYearMonthPreStr(-1,"yyyy年MM月",true);
        }
        return date;
    }

    /**
     * 获取当年月
     * @return
     */
    public static String getYearMonth() {
        String date = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        date = sf.format(calendar.getTime());
        return date;
    }

    private static String getYearMonthPreStr(int i,String pattern,Boolean isTongbi) {
        String date = "";
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(isTongbi) {
            calendar.add(Calendar.YEAR, -1);
        }
        calendar.add(Calendar.MONTH, i);
        date = sf.format(calendar.getTime());
        return date;
    }
}
