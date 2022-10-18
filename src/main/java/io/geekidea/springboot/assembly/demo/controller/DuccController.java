package io.geekidea.springboot.assembly.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.TestService;
import com.example.demo.thread.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;

@RestController
@RequestMapping("/test")
@Slf4j
public class DuccController {


    private static LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();


    @Autowired
    private TestService testService;


    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/a")
    public String getABTestRules(String a) {
        redisTemplate.opsForValue().set("name", a);
        redisTemplate.opsForList().rightPush("queue", a);
        return "OK";
    }

    @RequestMapping("/c")
    public String c(String a) {
        List queue = redisTemplate.opsForList().range("queue", 0, 100);

        return JSON.toJSONString(queue);
    }

    @RequestMapping("/d")
    public String d() {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.execute(new Runnable() {
            @Override
            public void run() {
                Long size = redisTemplate.opsForList().size("queue");
                System.out.println("size是：" + size);

                for (int i = 0; i < size; i++) {
                    String s = (String) redisTemplate.opsForList().leftPop("queue");
                    if (!StringUtils.isEmpty(s)) {

                        try {
                            System.out.println("输出的redis数据是：" + s + "   " + new Date());
                            Thread.sleep(3 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }

        });

        return JSON.toJSONString("ok");
    }


    /**
     * java将请求写入队列，再用异步消费该队列，高并发实战。
     * https://blog.csdn.net/u014373554/article/details/109473443
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/b/{id}")
    public String b(@PathVariable("id") Integer id) throws InterruptedException {
        if (deque.remainingCapacity() > 0) {
            deque.put(id);
        } else {
            return "队列已经满了,请稍后重试";
        }
        // 创建一个线程
        SaveUserLogQueueRunnable runnable = new SaveUserLogQueueRunnable(deque, testService);
        Constants.SCHEDULED_EXECUTOR_SERVICE.execute(runnable);
        return "OK";
    }


}
