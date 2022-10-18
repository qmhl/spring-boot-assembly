package io.geekidea.springboot.assembly.demo.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test3 {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = df.parse("2017-12-20 13:09:09");
//        //指定日期
//        Calendar cal = df.getCalendar();
//        //当前时间
//        Calendar cas = Calendar.getInstance();
//        Date time1 = cal.getTime();
//        Date time2 = cas.getTime();
//        String format1 = df.format(time1);
//        String format2 = df.format(time2);
//        System.out.println("format1: " + format1);
//        System.out.println("format2: " + format2);
//
//        cal.add(Calendar.HOUR, 2);
//        String format3 = df.format(cal.getTime());
//
//        System.out.println("format3: " + format3);


//        int year = cal.get(Calendar.YEAR);//获取年份
//        int month=cal.get(Calendar.MONTH);//获取月份
//        int day=cal.get(Calendar.DATE);//获取日
//        int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
//        int minute=cal.get(Calendar.MINUTE);//分
//        int second=cal.get(Calendar.SECOND);//秒
//        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
//        System.out.println("year "+year);
//        System.out.println("day "+day);
//        System.out.println("hour "+hour);
//        System.out.println("minute "+minute);
//        System.out.println("second "+second);
//        System.out.println("时分秒"+hour+":"+minute);
        //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$


//        Date d1 = df.parse("2017-12-20 13:09:09");
//        Date d2 = df.parse("2017-12-20 14:09:09");

        SimpleDateFormat HHmmssdf2 = new SimpleDateFormat("HH:mm:ss");
        Date d3 = HHmmssdf2.parse("13:09:09");
        Date d4 = HHmmssdf2.parse("14:09:09");
//        Date randomDate = getRandomDate(d3, d4);
//        System.out.println("randomDate" + HHmmssdf2.format(randomDate));

        long random = random(d3.getTime(), d4.getTime());
        System.out.println("xxx: " + HHmmssdf2.format(new Date(random)));
        System.out.println("xxx22: " + new Date(random));

    }


    public static Date getRandomDate(Date beginTime, Date endTime) {

        Integer randomTime = 10;

        List<Date> randomList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);


        while (beginTime.getTime() < endTime.getTime()) {
            calendar.add(Calendar.MINUTE, randomTime);
            randomList.add(beginTime);
            beginTime = calendar.getTime();
        }
        System.out.println("randomList :" + randomList);

        // 随机取集合中一位返回.get
        Random random = new Random();
        int index = random.nextInt(randomList.size());
        return randomList.get(index);
    }


    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        return rtn;
    }
}
