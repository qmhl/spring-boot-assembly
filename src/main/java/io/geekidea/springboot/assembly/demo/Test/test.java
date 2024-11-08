package io.geekidea.springboot.assembly.demo.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class test{
    public static void main(String[] arg){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");//格式化
        LocalDate time = LocalDate.parse("2022-01-01", sdf);//String转Date
        System.out.println(time);
        System.out.println(setWeek(time));
    }

    public static String setWeek(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        //周日为一周的开始
       // calendar.setFirstDayOfWeek(Calendar.SUNDAY);//设置周一为一周的开始
        SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfM = new SimpleDateFormat("MM");
        //LocalDate转Date
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Date srmProductDate =  Date.from(zonedDateTime.toInstant());
        calendar.setTime(srmProductDate);
        Integer year = Integer.valueOf(sdfY.format(srmProductDate));
        Integer month = Integer.valueOf(sdfM.format(srmProductDate));
        Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
        if(week == 1 && month == 12){
            return  year + 1 + (String.valueOf(week).length() == 1 ? "0" : "") + week;
        }else{
            return year + (String.valueOf(week).length() == 1 ? "0" : "") + week;
        }
    }

}