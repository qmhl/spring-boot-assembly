package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.Exception.BusinessException;
import com.example.demo.config.MapConfig;
import com.example.demo.dao.StrategyMapper;
import com.example.demo.entity.Strategy;
import com.example.demo.model.*;
import com.example.demo.service.ProjectService;
import com.example.demo.utils.DateUtilss;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {


    @Value("#{'${list}'.split(',')}")
    private List<String> list;

    @Value("#{${maps}}")
    private Map<String, String> maps;

//    @Value("#{${test.limitSizeMap}}")
//    private Map<String, String> testMap;

    @Autowired
    private MapConfig mapConfig;

    @Autowired
    private ProjectService projectService;


    @Autowired
    private StrategyMapper strategyMapper;

    @RequestMapping("/test0")
    public String test0() {
//        log.info("map is {}", JSON.toJSONString(testMap));
        log.info("list is {}", JSON.toJSONString(list));

        Map<String, List<String>> limitSizeMap = mapConfig.getLimitSizeMap();

        System.out.println("limitSizeMap读取成功，数据如下：");
        for (String key : limitSizeMap.keySet()) {
            System.out.println("key: " + key + ", value: " + limitSizeMap.get(key));
        }
        List<String> skuList = limitSizeMap.get("sku");
        List<List<String>> headList = new ArrayList<>();
        skuList.forEach(o -> {
            headList.add(Arrays.asList(o));
        });
        log.info("headList is {}", JSON.toJSONString(headList));


        System.out.println("------");
        return JSON.toJSONString(headList);
    }


    @RequestMapping("/test1")
    public String A(@RequestBody IntentInsightQuery param) {
        log.info("入参 is {}", JSON.toJSONString(param));
        PermissionCheckParam father = param;
        log.info("father is {}", JSON.toJSONString(father));

        return JSON.toJSONString(father);
    }


    @RequestMapping("/test2")
    public String test2(@RequestBody Son son) {
        log.info("test2 入参 is {}", JSON.toJSONString(son));
        return JSON.toJSONString(son);
    }


    @RequestMapping("/test3")
    public String test2(Father father) {
        log.info("test3 入参 is {}", JSON.toJSONString(father));
        return JSON.toJSONString(father);
    }


    @GetMapping(value = "/delete")
    public RestResponse delete() {

        try {
            if (projectService.deleteProject(1, "李四")) {
                return RestResponse.ok("删除成功");
            } else {
                return RestResponse.failure("删除失败");
            }
        } catch (BusinessException e) {
            log.warn("delete 失败：", e);
            return RestResponse.failure(e.getMessage());
        } catch (Exception e) {
            log.warn("delete 出现异常：", e);
            return RestResponse.failure("删除项目失败");
        }
    }


    @RequestMapping("/test/insert")
    public String insert() {
        Strategy strategy = new Strategy();
        strategy.setId(6);
        strategy.setName("策略11");
        strategy.setDescription("描述11");

        int i = strategyMapper.insert(strategy);
        return JSON.toJSONString(i);
    }


    @RequestMapping("/ruleTest")
    public String ruleTest() {
        Strategy strategy = new Strategy();
        strategy.setRuleUrl("sex");
        Strategy i = strategyMapper.selectTest(strategy);
        return JSON.toJSONString(i);
    }


    @RequestMapping("/setTimeRange")
    public String setTimeRange1(@RequestBody FeelInsightParam param) throws ParseException {
        setTimeRange(param);
        return JSON.toJSONString(param.getDtTimes());
    }


    private void setTimeRange(FeelInsightParam param) throws ParseException {
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
