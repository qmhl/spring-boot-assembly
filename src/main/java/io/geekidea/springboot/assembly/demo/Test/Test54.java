package io.geekidea.springboot.assembly.demo.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test54 {


    public static void main(String[] args) throws IOException, ParseException {
        String today = "2022-01-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        // 在四天以上才算入下一周，否则为上一个周
//        calendar.setMinimalDaysInFirstWeek(4);
        // 设置一周的开始为周几，设置为周一
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        String res = "";
        if (week < 10) {
            res = year + "-" + "0" + week;
        }else{
            res= year + "-" +  week;
        }
        System.out.println(res);


//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请键入日期（如：2008-8-8）：");
//        String str = br.readLine();
//        Date date = dateFormatter.parse(str);
////        dateFormatter.applyPattern("D");//
////        System.out.println("一年中的第几天：" + dateFormatter.format(date));
////        dateFormatter.applyPattern("d");
////        System.out.println("一个月中的第几天：" + dateFormatter.format(date));
//        dateFormatter.applyPattern("w");
//        System.out.println("一年中的第几周：" + dateFormatter.format(date));
////        dateFormatter.applyPattern("W");
////        System.out.println("一个月中的第几周：" + dateFormatter.format(date));
////        dateFormatter.applyPattern("E");
////        System.out.println("一个星期中的天数：" + dateFormatter.format(date));
//        br.close();
    }
}
