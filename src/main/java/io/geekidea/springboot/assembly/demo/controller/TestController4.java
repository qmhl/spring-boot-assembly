package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.geekidea.springboot.assembly.demo.utils.HttpClientUtils;
import io.geekidea.springboot.assembly.demo.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test4")
@Slf4j
public class TestController4 {



    @PostMapping("/postFormTest")
    public Long postFormTest(@RequestParam("id") Long id) throws IOException {
        log.info(" postForm  >>>{}",id);
        return id;
    }

    @PostMapping("/postForm")
    public Long postForm(@RequestParam("id") Long id) throws IOException {
        String url="http://localhost:8080/example/test4/postFormTest";
        Map<String, String> map = new HashMap<>();
        map.put("id",id+"");
        String res = HttpClientUtils.postForm(url, map, map);
        log.info(" postForm res >>>{}",res);
        return id;
    }



    @PostMapping("/postForm22")
    public Long postForm22(@RequestParam("id") Long id) throws IOException {
        String url="http://localhost:8080/example/test4/postFormTest";
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("id", id);
        log.info("请求bdp-create 任务入参是：{}", JSON.toJSONString(form));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String result = RestTemplateUtils.requestFormByPost(url, form, restTemplate, headers);
        log.info("请求bdpTask的请求结果: {}", result);
        log.info(" postForm22 res >>>{}",result);
        return id;
    }



    @PostMapping("/postForm6666")
    public Long headerMap(@RequestParam("id") Long id) throws IOException {
        // 获取所有的请求头
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> headerMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headerMap.put(name,value);
        }
        log.info(" postForm6666 res >>>{}",headerMap);
        return id;
    }


}
