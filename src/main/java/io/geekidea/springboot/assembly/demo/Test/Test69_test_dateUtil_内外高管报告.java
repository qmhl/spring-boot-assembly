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
public class Test69_test_dateUtil_内外高管报告 {


    private final static String YYYY_MM_DD = "yyyy-MM-dd";
    private final static String YYYY_MM = "yyyy-MM";


    public static void main(String[] args) throws Exception {
        String date1 = "2023-02-21";
        //获取上周的起始  结束

        String date2 = "2023-06-21";

//        DateUtil.getDateForMonthAfterCal()

        String preWeek = DateUtil.getCurrentWeek1(date1, YYYY_MM_DD);
        List<String> weekList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(preWeek);
        System.out.println("当前周1的 开始时间：" + weekList.get(0));
        System.out.println("当前周1的 结束时间：" + weekList.get(1));


        String week2 = DateUtil.getBeginAndEndTimeOfLastWeek(date1, YYYY_MM_DD);
        List<String> week2List = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(week2);
        System.out.println("上一周的 开始时间2：" + week2List.get(0));
        System.out.println("上一周的 结束时间2：" + week2List.get(1));


        String curWeek = DateUtil.getCurrentWeek(date1, YYYY_MM_DD);
        List<String> curWeekList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(preWeek);
        System.out.println("当前周的 开始时间：" + curWeekList.get(0));
        System.out.println("当前周的 结束时间：" + curWeekList.get(1));
        //**************每月********************
        //上个月的开始 结束时间
        List<String> str7 = DateUtil.getMonthTime(date1);
        System.out.println("/上个月的开始 结束时间：" + str7);

        String preStartMonth = DateUtil.getPreStartMonth(date1, YYYY_MM_DD);
        String preEndMonth = DateUtil.getPreEndMonth(date1, YYYY_MM_DD);
        System.out.println("/上个月的 开始时间：" + preStartMonth);
        System.out.println("/上个月的 结束时间：" + preEndMonth);


        //后面每个月的开始时间 结束时间
        String str8 = DateUtil.getDateForMonthAfterCalWithPattern(str7.get(0), 1, "yyyy-MM-dd");
        String str9 = DateUtil.getDateForMonthAfterCalWithPattern(str7.get(1), 1, "yyyy-MM-dd");
        System.out.println("往后N个月的开始 开始时间：" + str8);
        System.out.println("往后N个月的开始 结束时间：" + str9);

        //每季度
        String prestartQuarter = DateUtil.getPreStartQuarter(date1, YYYY_MM_DD);
        String preEndQuarter = DateUtil.getPreEndQuarter(date1, YYYY_MM_DD);
        System.out.println("上一个季度 开始时间：" + prestartQuarter);
        System.out.println("上一个季度 结束时间：" + preEndQuarter);
        //每半年
        String preStartHalfYear = DateUtil.getPreStartHalfYear(date1, YYYY_MM_DD);
        String preEndHalfYear = DateUtil.getPreEndHalfYear(date1, YYYY_MM_DD);
        System.out.println("上一个半年 开始时间：" + preStartHalfYear);
        System.out.println("上一个半年 结束时间：" + preEndHalfYear);

//

        List<String> allWeekList = getAllDateList(date1, date2, "week");
        System.out.println("两个日期之间的所有周的开始 结束日期 " + JSON.toJSONString(allWeekList));

        List<String> allMonthList = getAllDateList(date1, date2, "month");
        System.out.println("两个日期之间的所有月份的开始 结束日期 " + JSON.toJSONString(allMonthList));

        List<String> allQuarterList = getAllDateList(date1, date2, "quarter");
        System.out.println("两个日期之间的所有季度的开始 结束日期 " + JSON.toJSONString(allQuarterList));

        List<String> allHalfYearList = getAllDateList(date1, date2, "halfYear");
        System.out.println("两个日期之间的所有半年的开始 结束日期 " + JSON.toJSONString(allHalfYearList));
    }


    /**
     * 适用于内外部高管报告 日期格式：2022-11-22
     * 这里分析周期只有 仅一次:only,每周:week,每月:month,每季:quarter,每半年:halfYear
     *
     * @param start 有效开始时间
     * @param end   过期结束时间
     * @param type  分析周期：仅一次:only,每周:week,每月:month,每季:quarter,每半年:halfYear,上个月:preMonth 上周:preWeek
     * @return
     * @throws ParseException
     */
    public static List<String> getAllDateList(String start, String end, String type) throws ParseException {
        List<String> list = new LinkedList<>();
        if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calStart = Calendar.getInstance();
            Calendar calEND = Calendar.getInstance();
            calStart.setTime(sdf.parse(start));
            calEND.setTime(sdf.parse(end));

            while (calStart.before(calEND) || calStart.equals(calEND)) {
                if ("only".equals(type)) {
                    list.add(Joiner.on(",").join(start, end));
                    return list;
                }

                if ("week".equals(type)) {
                    // 获取前一周的开始 结束日期
                    String preWeek = DateUtil.getBeginAndEndTimeOfLastWeek(start, YYYY_MM_DD);
                    List<String> weekList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(preWeek);
                    list.add(Joiner.on(",").skipNulls().join(weekList));
                    // 入参往后加一周
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "week", 1);
                    calStart.add(Calendar.WEEK_OF_YEAR, 1);
                }


                if ("month".equals(type)) {
                    // 获取前一个月的开始 结束日期
                    String preStartMonth = DateUtil.getPreStartMonth(start, YYYY_MM_DD);
                    String preEndMonth = DateUtil.getPreEndMonth(start, YYYY_MM_DD);
                    list.add(Joiner.on(",").join(preStartMonth, preEndMonth));

                    // 入参往后追加一个月
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "month", 1);
                    calStart.add(Calendar.MONTH, 1);
                }
                if ("quarter".equals(type)) {
                    // 获取前一个季度的开始 结束日期
                    String preStartQua = DateUtil.getPreStartQuarter(start, YYYY_MM_DD);
                    String preEndQua = DateUtil.getPreEndQuarter(start, YYYY_MM_DD);
                    list.add(Joiner.on(",").join(preStartQua, preEndQua));

                    // 入参往后加一个季度
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "month", 3);
                    calStart.add(Calendar.MONTH, 3);
                }
                if ("halfYear".equals(type)) {
                    // 获取前一个半年周期的开始 结束日期
                    String preStartHalfYear = DateUtil.getPreStartHalfYear(start, YYYY_MM_DD);
                    String preEndHalfYear = DateUtil.getPreEndHalfYear(start, YYYY_MM_DD);
                    list.add(Joiner.on(",").join(preStartHalfYear, preEndHalfYear));

                    // 入参往后追加半年
                    start = DateUtil.getLastYearOrMonthOrDate(start, YYYY_MM_DD, "month", 6);
                    calStart.add(Calendar.MONTH, 6);
                }
            }
        }
        return list;
    }
}



