package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springboot.assembly.demo.entity.Apple;
import io.geekidea.springboot.assembly.demo.model.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test3")
@Slf4j
public class TestController3 {


    @GetMapping("/query1")
    public RestResponse query1(Long id) throws IOException {
        return RestResponse.ok(id );
    }
    @PostMapping("/query")
    public RestResponse query(@RequestParam("id") Long id, @RequestBody List<Apple> apple) throws IOException {
        log.info(" test  >>>{}", JSON.toJSONString(apple));
        return RestResponse.ok(id + ": " + JSON.toJSONString(apple));
    }


    @GetMapping("/array")
    public RestResponse array(List<List<Integer>> cidList) throws IOException {
        log.info(" array  >>>{}", JSON.toJSONString(cidList));
        return RestResponse.ok(cidList + ": " + JSON.toJSONString(cidList));
    }

    @PostMapping("/array1")
    public RestResponse array1(@RequestParam("idLists[]") List<List<Integer>> cidList) throws IOException {
        log.info(" array1  >>>{}", JSON.toJSONString(cidList));
        return RestResponse.ok(cidList + ": " + JSON.toJSONString(cidList));
    }

    @PostMapping("/array2")
    public RestResponse array2(@RequestParam("idLists") List<Integer> cidList) throws IOException {
        log.info(" array2  >>>{}", JSON.toJSONString(cidList));
        return RestResponse.ok(cidList + ": " + JSON.toJSONString(cidList));
    }

    @PostMapping("/array3")
    public RestResponse array3(@RequestBody List<List<Integer>> cidList) throws IOException {
        log.info(" array3  >>>{}", JSON.toJSONString(cidList));
        return RestResponse.ok(cidList + ": " + JSON.toJSONString(cidList));
    }


    @PostMapping("/excuteSql")
    public RestResponse excuteSql(@RequestBody Map<String,Object> map) throws IOException {
        log.info(" excuteSql  >>>{}", JSON.toJSONString(map));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        log.info(" excuteSql  sql >>>{}", jsonObject.get("sql"));
        return RestResponse.ok(map);
    }

}
