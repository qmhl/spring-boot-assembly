package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.utils.DatePatternEnum;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import io.geekidea.springboot.assembly.demo.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class Test_89 {
    public static void main(String[] args) throws ParseException {
        String s="compare_uat:2023-12-21-17";
        System.out.println(StringUtils.substringAfter(s,"compare_uat:"));

        Long dateTime = getDateTime(20);
        System.out.println(dateTime);
        long l = convertToTimestamp("2023-12-17 12:00:00");
        System.out.println(l);
    }


    public static Long getDateTime(int hour){
        SimpleDateFormat sdf=new SimpleDateFormat(DatePatternEnum.DATE_FORMAT_24H.pattern);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime().getTime();
    }

    /**
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static long convertToTimestamp(String timeStr) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(DatePatternEnum.DATE_FORMAT_24H.pattern);
        Date date = sdf.parse(timeStr);
        return date.getTime();
    }
}



