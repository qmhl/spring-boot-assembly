package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Test70_test_dateUtil_品类洞察报告 {


    private final static String YYYY_MM = "yyyy-MM";
    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception {
        // 执行计划的开始时间
        String date1 = "2023-04-03 10:00:00 ";
        // 过期时间
        String date2 = "2023-09-01";
        // 执行计划的结束时间
        String endDate = "2023-08-03 10:00:00";

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


        //如果最后一次执行时间 小于 过期时间，则将最后一次执行时间作为分析周期的结束时间
        if (shortSdf.parse(endDate).getTime() < shortSdf.parse(date2).getTime()) {
            List<String> allMonthList = getBetweenDateList(date1, endDate, "month");
            System.out.println("两个日期之间的所有月份的开始 结束日期 " + JSON.toJSONString(allMonthList));

        } else {
            List<String> allMonthList = getBetweenDateList(date1, date2, "month");
            System.out.println("两个日期之间的所有月份的开始 结束日期 " + JSON.toJSONString(allMonthList));
        }

        System.out.println("=================================== ");

//        List<String> allMonthList = getBetweenDateList(date1, date2, "month");
//        System.out.println("两个日期之间的所有月份的开始 结束日期 " + JSON.toJSONString(allMonthList));

        List<String> allQuarterList = getBetweenDateList(date1, date2, "quarter");
        System.out.println("两个日期之间的所有季度的开始 结束日期 " + JSON.toJSONString(allQuarterList));

        List<String> allHalfYearList = getBetweenDateList(date1, date2, "halfYear");
        System.out.println("两个日期之间的所有半年的开始 结束日期 " + JSON.toJSONString(allHalfYearList));
    }


    /**
     * 适用于品类洞察报告  月格式：2022-01
     * 这里分析周期只有 仅一次:only,每月:month,每季:quarter,每半年:halfYear
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
                if ("only".equals(type)) {
                    list.add(Joiner.on(",").join(start, end));
                    return list;
                }
                if ("month".equals(type)) {
                    // 获取前一个月的日期
                    String month = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", -1);
                    list.add(month);

                    // 入参往后追加一个月
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", 1);
                    calStart.add(Calendar.MONTH, 1);
                }
                if ("quarter".equals(type)) {
                    // 获取前一个季度的开始 结束日期
                    String preStartQua = DateUtil.getPreStartQuarter(start, YYYY_MM);
                    String preEndQua = DateUtil.getPreEndQuarter(start, YYYY_MM);
                    List<String> quarter = DateUtil.getDateListWithPattern(preStartQua, preEndQua, "month", YYYY_MM);
                    list.add(Joiner.on(",").skipNulls().join(quarter));

                    // 入参往后加一个月
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", 3);
                    calStart.add(Calendar.MONTH, 3);
                }
                if ("halfYear".equals(type)) {
                    // 获取前一个季度的开始 结束日期
                    String preStartHaflYear = DateUtil.getPreStartHalfYear(start, YYYY_MM);
                    String preEndHaflYear = DateUtil.getPreEndHalfYear(start, YYYY_MM);
                    List<String> halfYear = DateUtil.getDateListWithPattern(preStartHaflYear, preEndHaflYear, "month", YYYY_MM);
                    list.add(Joiner.on(",").skipNulls().join(halfYear));
                    // 入参往后加一个月
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM, "month", 6);
                    calStart.add(Calendar.MONTH, 6);
                }
            }
        }
        return list;
    }
}



