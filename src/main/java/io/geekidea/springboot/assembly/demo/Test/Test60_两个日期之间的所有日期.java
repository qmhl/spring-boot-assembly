package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Test60_两个日期之间的所有日期 {

    public static void main(String[] args) throws ParseException {
        String dateForMonthAfterCal = getDateForMonthAfterCal("2022-11", -12);
        System.out.println(dateForMonthAfterCal);
        System.out.println("==========");
        List<String> dateList = getDateListWithPattern("2022-01-01", "2022-02-01","date","yyyy-MM-dd");
        System.out.println(dateList);
        System.out.println("=========");

        List<String> dtList = Arrays.asList("2022-02", "2022-08", "2022-06");
        String collect = dtList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0);
        System.out.println(collect);


    }
    public static List<String> getDateListWithPattern(String start, String end,String type,String pattern) throws ParseException {
        List<String> list = new LinkedList<>();
        if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar calendar = Calendar.getInstance();
            Calendar calendarEND = Calendar.getInstance();
            calendar.setTime(sdf.parse(start));
            calendarEND.setTime(sdf.parse(end));
            while (calendar.before(calendarEND) || calendar.equals(calendarEND)) {
                list.add(sdf.format(calendar.getTime()));
                if("month".equals(type)){
                    calendar.add(Calendar.MONTH, 1);
                }
                if("year".equals(type)){
                    calendar.add(Calendar.YEAR, 1);
                }else{
                    calendar.add(Calendar.DATE, 1);
                }

            }
        }
        return list;
    }


    /**
     * 获取某个日期前后推移后的时间(按月)
     */
    public static String getDateForMonthAfterCal(String date, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
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



}



