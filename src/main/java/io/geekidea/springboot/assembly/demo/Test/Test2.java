package io.geekidea.springboot.assembly.demo.Test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {

    public static void main(String[] args) throws ParseException {
        String format = "HH:mm:ss";
        Date nowTime = new SimpleDateFormat(format).parse("09:27:02");
        Date startTime = new SimpleDateFormat(format).parse("09:27:00");
        Date endTime = new SimpleDateFormat(format).parse("09:27:59");
//        System.out.println(isEffectiveDate(nowTime, startTime, endTime));
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


//    public static boolean test(Date nowTime, Date startTime, Date endTime) throws ParseException {
//
//
//    }
}
