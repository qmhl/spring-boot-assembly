package io.geekidea.springboot.assembly.demo.thread;

import io.geekidea.springboot.assembly.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@Slf4j
public class Testhread extends Thread {


    @Override
    public void run() {
        try {
            ArrayList list = TestUtils.list;
            for (Object o : list) {
                System.out.println("输出的元素是："+o);
            }
        } catch (Exception e) {
            System.out.println("【Testhread   】处理队列出现错误如下：");
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void init() {
        this.start();
    }


}