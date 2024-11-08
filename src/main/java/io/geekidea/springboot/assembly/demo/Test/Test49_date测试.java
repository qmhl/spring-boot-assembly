package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Test49_date测试 {


    public static void main(String[] args) throws Exception {
        String date1 = "2022-10-22";
        String date2 = "2022-10-25";
        //中间间隔多少天
        long days = DateUtil.getBetweenDays(date1, date2);
        System.out.println("相隔天数：" + days);
        //往后推移天数
        String str1 = DateUtil.getDateAfterCal(date1, 6);
        System.out.println("/往后推移天数：" + str1);

        String str2 = DateUtil.getDateAfterCal(date1, -6);
        System.out.println("/往前推移天数：" + str2);
        //往后推移月数
        String str3 = DateUtil.getDateForMonthAfterCal(date1, 1);
        System.out.println("/往后推移月数：" + str3);
        //往前推移月数
        String str4 = DateUtil.getDateForMonthAfterCal(date1, -1);
        System.out.println("/往前推移月数：" + str4);

        //====================
        //两个日期后推
//        List str5 = DateUtil.getDateListAfterCal(date1, date2, 5);
//        System.out.println("/两个日期后推：" + str5);
//
//        //两个日期前推
//        List str6 = DateUtil.getDateListAfterCal(date1, date2, -5);
//        System.out.println("/两个日期前推：" + str6);
        // 下面方式和上面作用是一样的
//        if (!CollectionUtils.isEmpty(param.getDtTimes()) && !CollectionUtils.isEmpty(param.getDtTimes().get(0))) {
//            List<String> dtTime = new ArrayList<>();
//            List<List<String>> dtTimes = new ArrayList<>();
//            dtTime.add(DateUtil.getDateAfterCal(param.getDtTimes().get(0).get(0), -day));
//            dtTime.add(DateUtil.getDateAfterCal(param.getDtTimes().get(0).get(1), -day));
//            dtTimes.add(dtTime);
//            param.setDtTimes(dtTimes);
//        }

        //====================

        // 计算月力度的 上个月的开始 结束时间
        List str7 = DateUtil.getMonthTime(date1);
        System.out.println("/上个月的开始 结束时间：" + str7);

        // 计算月力度的 上个月的开始 结束时间
//        String str71 = DateUtil.getPreMonth(date1);
//        System.out.println("/上个月的开始 结束时间：" + str71);


        // 计算去年的开始时间
        String str8 = DateUtil.getLastYearDate(date1);
        System.out.println("/去年这个时间：" + str8);

//        getLastYearDate

        String strPa = parseByPattern("2022-10-09", "yyyy-MM");
        System.out.println(strPa);


        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("2022-10-01", "2022-10-31"));
        list.add(Arrays.asList("2022-11-01", "2022-11-30"));

        List<List<String>> strPa2 = handleTimeForQcrMonth(list);
        System.out.println(JSON.toJSONString(strPa2));

        String str9 = DateUtil.getLastYearOrMonthOrDate("2022-10", "yyyy-MM", "year", -1);
        String str10 = DateUtil.getLastYearOrMonthOrDate("2022-10", "yyyy-MM", "month", -1);
        System.out.println("/str9：" + str9);
        System.out.println("/str9：" + str10);

        String replace = StringUtils.replace(str9, "-","");
        System.out.println(replace);


    }


    public static String parseByPattern(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(sdf.parse(date));
        } catch (Exception e) {
            return null;
        }
    }


    private static List<List<String>> handleTimeForQcrMonth(List<List<String>> dtTimes) {
        List<List<String>> resList = new ArrayList<>();
        for (List<String> dtTime : dtTimes) {
            List<String> list = new ArrayList<>();
            list.add(parseByPattern(dtTime.get(0), "yyyy-MM"));
            list.add(parseByPattern(dtTime.get(1), "yyyy-MM"));
            resList.add(list);
        }
        return resList;

    }

}


