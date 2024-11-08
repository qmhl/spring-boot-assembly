package io.geekidea.springboot.assembly.demo.controller;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;


@RestController
@RequestMapping("/test6666")
@Slf4j
public class TestController_获取所有请求头信息 {

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
