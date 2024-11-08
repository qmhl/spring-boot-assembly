package io.geekidea.springboot.assembly.demo.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test54_获取第N周 {


    public static void main(String[] args) throws IOException, ParseException {
        String today = "2022-11-21";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
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
        System.out.println("week: " + week);
        System.out.println("month: " + month);
        System.out.println("year: " + year);

        String res = "";
        //如果是跨年，尤其是xxxx-01-01情况，如果周大于52，应该是上一年的；否则，是这一年的
        if (week >= 52 && month == 1) {
            res = (year - 1) + "" + week;
        } else {
            res = year + "" + (week < 10 ? "0" + week : week);
        }
        System.out.println(res);


//        int weekNum = getWeekNum(date);
//        System.out.println(weekNum);
//
//
//        int weekNum2 = getWeekNum2(today);
//        System.out.println(weekNum2);
//
//
//        String weekNum3 = getWeekNum3(today);
//        System.out.println(weekNum3);

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


    public static int getWeekNum(Date date) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        return g.get(Calendar.WEEK_OF_YEAR); //获得周数
    }

    public static int getWeekNum2(String dt) {
        LocalDate date = LocalDate.parse(dt);
        // WeekFields.ISO = 一星期从周一开始算, 其它的请自己摸索.
        WeekFields weekFields = WeekFields.ISO;
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }

    public static String getWeekNum3(String dt) {
        String str = "";
        LocalDate date = LocalDate.parse(dt);
        // WeekFields.ISO = 一星期从周一开始算, 其它的请自己摸索.
        WeekFields weekFields = WeekFields.ISO;
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        int year = date.getYear();
        int monthValue = date.getMonthValue();
        System.out.println("year: " + year);
        System.out.println("monthValue: " + monthValue);

        if (weekNumber >= 52 && monthValue == 1) {
            str = year - 1 + "-" + weekNumber;
        } else {
            str = year + "-" + weekNumber;

        }
        return str;
    }


}
