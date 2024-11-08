package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.utils.DatePatternEnum;
import io.geekidea.springboot.assembly.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Test_87_time {
    public static void main(String[] args) {
        Date parse = DateUtil.parse("2023-11-01 17:31:02", DatePatternEnum.DATE_FORMAT_24H);
        dealNextExecuteTime(parse,24);
    }
    public static  void dealNextExecuteTime( Date nextExecuteTime, Integer calculatePeriod) {
            Date date = DateUtil.addNHour(new Date(), calculatePeriod);
            Date resultDate = DateUtil.addNHour(date, -1);
            log.info("最终时间：{}", DateUtil.format(resultDate, DatePatternEnum.DATE_FORMAT_24H));
            log.info("original nextExecuteTime:{}", DateUtil.format(nextExecuteTime, DatePatternEnum.DATE_FORMAT_24H));
            String yyyyMMddHH = DateUtil.format(resultDate, DatePatternEnum.DATE_FORMAT_HH);
            String hhSS = DateUtil.format(nextExecuteTime, DatePatternEnum.DATE_FORMAT_XIAO_HH_SS);
            String nextExecuteTimeString = yyyyMMddHH + ":" + hhSS;
            log.info("nextExecuteTimeString:{}", nextExecuteTimeString);

    }


}



