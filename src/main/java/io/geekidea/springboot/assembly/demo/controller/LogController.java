package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {


    @RequestMapping("/test")
    public String A() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "success";
    }

    @RequestMapping("/test1")
    public String B() {
        // 假如日志级别是info  则info warn error都会输出
        if (log.isDebugEnabled()) {
            log.debug("debug");
        }
        if (log.isInfoEnabled()) {
            log.info("info");
        }
        if (log.isWarnEnabled()) {
            log.warn("warn");
        }
        if (log.isErrorEnabled()) {
            log.error("error");
        }


        log.debug(">>>debug");
        log.info(">>>info");
        log.warn(">>>warn");
        log.error(">>>error");
        return "success";
    }

}
