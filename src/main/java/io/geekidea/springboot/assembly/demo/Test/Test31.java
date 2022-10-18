package io.geekidea.springboot.assembly.demo.Test;


import com.example.demo.model.DateTypeEnum;
import com.example.demo.model.FeelInsightParam;
import com.example.demo.utils.DateUtilss;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/justdoit_potato/article/details/120022084
 */
@Slf4j
public class Test31 {


    public static void main(String[] args) throws Exception {

    }


    private static void setTimeRange(FeelInsightParam param) throws ParseException {
        //重写时间筛选处理逻辑
        //日期数组
        List<String> dateValues = param.getDateValues();
        if (!CollectionUtils.isEmpty(dateValues)) {
            if (DateTypeEnum.CUSTOM.getKey().equals(param.getDateType()) && dateValues.size() > 1) {
                //case1 自定义时间（日期数组里存放的是开始、结束时间）
                //计算时间差
                long betweenDays = DateUtilss.getBetweenDays(dateValues.get(0), dateValues.get(1));
                //判断一下开始结束时间差不能大于180天
                if (betweenDays <= 180) {
                    param.getDtTimes().add(dateValues);
                } else {
                    throw new IllegalArgumentException("查询天数超过最大限制180天!");
                }
            } else if (DateTypeEnum.WEEK.getKey().equals(param.getDateType())) {
                //case2 按周筛选（日期数组里存放的是某一周的开始时间，多选）
                //判断一下日期数组里不能超过于25个元素
                if (dateValues.size() <= 25) {
                    //遍历，每个日期需要补7天，分别放入dtTimes
                    dateValues.forEach(date -> {
                        //此时date是每个周的开始时间
                        List<String> everyWeek = new ArrayList<>();
                        String date2 = DateUtilss.getDateAfterCal(date, 6);
                        //注入参数
                        everyWeek.add(date);
                        everyWeek.add(date2);
                        param.getDtTimes().add(everyWeek);
                    });
                } else {
                    throw new IllegalArgumentException("查询天数超过最大限制180天!");
                }
            } else if (DateTypeEnum.MONTH.getKey().equals(param.getDateType())) {
                //case3 按月筛选（日期数组里存放的是某个月的开始时间，多选）
                //判断一下日期数组里不能超过于6个元素
                if (dateValues.size() <= 6) {
                    //遍历，每个日期需要计算出对应的（每个月的）结束时间，分别放入dtTimes
                    dateValues.forEach(date -> {
                        //算出当前月有多少天
                        List<String> dates = Arrays.asList(date.split("-"));
                        if (!CollectionUtils.isEmpty(dates) && dates.size() == 3) {
                            List<String> times = DateUtilss.getFormatTime(Integer.valueOf(dates.get(0)), Integer.valueOf(dates.get(1)));
                            param.getDtTimes().add(times);
                        } else {
                            throw new IllegalArgumentException("按月筛选-页面传参错误！");
                        }
                    });
                } else {
                    throw new IllegalArgumentException("查询天数超过最大限制180天!");
                }

            }
        }
    }



}
