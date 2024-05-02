package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_95 {
    public static void main(String[] args) {
        Long time1= new Date().getTime();
        System.out.println(time1);
        Date date = new Date(time1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println(format);

        Long time2= 1713868092L;
        Date date2 = new Date(time2*1000);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = sdf2.format(date2);
        System.out.println(format2);

        String  url="http://storage.jd.local/fusion/request/202404161552112056592257108806180993.txt";
        int i = url.lastIndexOf("/");
        String fileName = url.substring(i + 1);
        System.out.println(fileName);

    }


}



