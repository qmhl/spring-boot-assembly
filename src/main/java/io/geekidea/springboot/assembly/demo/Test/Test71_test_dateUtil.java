package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import io.geekidea.springboot.assembly.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Test71_test_dateUtil {


    private final static String YYYY_MM = "yyyy-MM";
    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static void main(String[] args) throws Exception {
        String date1 = "2023-02-21";
        //获取上周的起始  结束

        String date2 = "2023-05-05 14:45:00";
        SimpleDateFormat sfm = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date start = sfm.parse(date2);
        Date nextValidTime = getNextValidTime(start, 1);
        System.out.println(sfm.format(nextValidTime));

        System.out.println("==============url=========");
        String url="https://selection-platform.s3.cn-north-1.jdcloud-oss.com/xycenter/20230407180829434226682105814924240.txt";
        String s = FileUtil.cutUrlKey(url);
        System.out.println(s);

    }

    public  static  Date getNextValidTime(Date date, int num){
        Date end = new Date();
        while (date.before(end) || date.equals(end)) {
            date = DateUtil.addNHour(date, num);
        }
        return date;
    }
}



