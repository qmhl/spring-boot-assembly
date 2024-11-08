package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.utils.DatePatternEnum;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import io.geekidea.springboot.assembly.demo.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test_88 {
    public static void main(String[] args) {
//        Date parse = DateUtil.parse("2023-11-01 17:31:02", DatePatternEnum.DATE_FORMAT_24H);
        Date date = judgeStartTime("2023-11-01 17:31:02", 1);
        System.out.println(DateUtil.format(date, DatePatternEnum.DATE_FORMAT_24H));

        Integer a = 101;
        Integer b = 309;
        System.out.println((float) a / b*100);

        String s="{\"ruleVersion\":\"1\",\"biUnlimit\":0}";

    }

    public static Date judgeStartTime(String startTime, Integer calculatePeriod) {
        Date date = new Date();
        if (ObjectUtil.isEmpty(calculatePeriod)) {
            throw new RuntimeException("calculatePeriod is null ");
        }
        // 如果 开始时间为null 则取当前时间
        if (ObjectUtil.isEmpty(startTime)) {
            return DateUtil.addNHour(date, calculatePeriod);
        }
        Date start = DateUtil.parse(startTime, DatePatternEnum.DATE_FORMAT_24H);
        // 开始时间>系统时间
        if (ObjectUtil.isNotEmpty(start) && start.after(date)) {
            return start;
        } else {
            // 当前时间-开始时间 ÷ 周期 向上取整
            DecimalFormat df = new DecimalFormat("0.00000000");
//            log.info("time numFloat {}",date.getTime() - start.getTime());
//            log.info("calculatePeriod numFloat {}",calculatePeriod);
            String numFloat = df.format(((float) (date.getTime() - start.getTime()) / (calculatePeriod * 3600 * 1000)));
//            log.info("float numFloat {}",numFloat);
            int num = (int) Math.round(Math.ceil(Float.parseFloat(numFloat)));
//            log.info("float num {}",num);
            return DateUtil.addNHour(start, num * calculatePeriod);
        }
    }


}



