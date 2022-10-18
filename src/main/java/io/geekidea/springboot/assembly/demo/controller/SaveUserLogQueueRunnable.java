package io.geekidea.springboot.assembly.demo.controller;

import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


@Slf4j
public class SaveUserLogQueueRunnable implements Runnable {


    private LinkedBlockingDeque<Integer> deque;

    private TestService testService;


    /**
     * 通过构造函数可以传参
     *
     * @param deque
     */
    public SaveUserLogQueueRunnable(LinkedBlockingDeque<Integer> deque, TestService testService) {
        this.deque = deque;
        this.testService = testService;
    }


    @Override
    public void run() {
        try {
            //开始处理请求队列中的请求,按照队列的FIFO的规则,先处理先放入到队列中的请求
            while (deque != null && deque.size() > 0) {
                Integer take = deque.take(); //取出队列中的请求
                Thread.sleep(3 * 1000);
                addLog(take); //处理请求
            }
        } catch (Exception e) {
            System.out.println("【SaveUserLogQueueRunnable   】处理队列出现错误如下：");
            e.printStackTrace();
        }

    }

    public synchronized void addLog(Integer value) {
        String time1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String s = testService.test1();
        System.out.println("value的大小： " + value + "----时间为： " + time1);
        System.out.println("调用service的值： " + s);

    }
}