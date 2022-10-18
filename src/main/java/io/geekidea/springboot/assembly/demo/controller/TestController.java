package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.config.MapConfig;
import io.geekidea.springboot.assembly.demo.dao.StrategyMapper;
import io.geekidea.springboot.assembly.demo.service.ProjectService;
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


}
