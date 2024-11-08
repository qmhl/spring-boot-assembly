package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Test71_test_dateUtil_排行榜 {


    private final static String YYYY_MM = "yyyy-MM";
    private final static String YYYY_MM_DD = "yyyy-MM-dd";


    public static void main(String[] args) throws Exception {
        String date1 = "2023-02-21";
        //获取上周的起始  结束

        String date2 = "2023-06-21";

//
        //**************每月********************
        //上个月的开始 结束时间
        String str7 = DateUtil.getLastYearOrMonthOrDate(date1, YYYY_MM, "month", -1);
        System.out.println("/上个月的开始 结束时间：" + str7);


        List<String> list = new ArrayList<>();

        List<String> monthList = DateUtil.getDateListWithPattern(date1, date2, "month", YYYY_MM);
        list.addAll(monthList);
        System.out.println("两个月份之间的所有月：" + monthList);


        List<String> monthList2 = DateUtil.getMonthBetweenDate(date1, date2);
        list.addAll(monthList2);
        System.out.println("两个月份之间的所有月2：" + monthList2);


        //每季度
        String prestartQuarter = DateUtil.getPreStartQuarter(date1, YYYY_MM);
        String preEndQuarter = DateUtil.getPreEndQuarter(date1, YYYY_MM);
        System.out.println("上一个季度 开始时间：" + prestartQuarter);
        System.out.println("上一个季度 结束时间：" + preEndQuarter);
        //每半年
        String preStartHalfYear = DateUtil.getPreStartHalfYear(date1, YYYY_MM);
        String preEndHalfYear = DateUtil.getPreEndHalfYear(date1, YYYY_MM);
        System.out.println("上一个半年 开始时间：" + preStartHalfYear);
        System.out.println("上一个半年 结束时间：" + preEndHalfYear);


        List<String> allWeekList = getBetweenDateList(date1, date2, "preWeek");
        System.out.println("两个日期之间的所有周的开始 结束日期 " + JSON.toJSONString(allWeekList));

        List<String> allMonthList = getBetweenDateList(date1, date2, "preMonth");
        System.out.println("两个日期之间的所有月份的开始 结束日期 " + JSON.toJSONString(allMonthList));
    }


    /**
     * 适用于排行榜报告 周格式： 202245  月格式：2022-10
     * 这里分析周期只有 每月:month,每季:quarter,每半年:halfYear
     *
     * @param start 有效开始时间
     * @param end   过期结束时间
     * @param type  分析周期：仅一次:only,每周:week,每月:month,每季:quarter,每半年:halfYear,上个月:preMonth 上周:preWeek
     * @return
     * @throws ParseException
     */
    public static List<String> getBetweenDateList(String start, String end, String type) throws ParseException {
        List<String> list = new LinkedList<>();
        if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM);
            Calendar calStart = Calendar.getInstance();
            Calendar calEND = Calendar.getInstance();
            calStart.setTime(sdf.parse(start));
            calEND.setTime(sdf.parse(end));
            while (calStart.before(calEND) || calStart.equals(calEND)) {
                if ("preWeek".equals(type)) {
                    // 获取前一个周的日期
                    String week = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "week", -1);
                    // 获取形如202245
                    String weekOfYear = DateUtil.getWeekOfYear(week);
                    list.add(weekOfYear);

                    // 入参往后追加一周
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "week", 1);
                    calStart.add(Calendar.WEEK_OF_YEAR, 1);
                }
                if ("preMonth".equals(type)) {
                    // 获取上一个月 形如YYYY_MM
                    String month = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", -1);
                    list.add(month);

                    // 入参往后追加一个月
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", 1);
                    calStart.add(Calendar.MONTH, 1);
                }
            }
        }
        return list;
    }
}



