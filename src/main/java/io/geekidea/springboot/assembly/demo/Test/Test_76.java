package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Test_76 {


    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date stageStartTime = TimeUtil.getHourTime(new Date(), 3);

        System.out.println(sdf.format(stageStartTime).toString());

        switchTest("A");
        switchTest("C");

        String xx = xx("xx");
        System.out.println(xx);
    }

    public static String xx(String flag) {
        try {
            String aa = "参数1";

            log.error(aa);
            return aa;
        } catch (Exception e) {
            log.error("参数2");
            return "参数22";
        } finally {
            log.error("参数3");
//            return "参数33";

        }
    }

    public static void switchTest(String flag) {

        switch (flag) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            case "C":
            case "D":
                System.out.println("D");
                break;
            default:
                System.out.println("other");
        }

    }
}



