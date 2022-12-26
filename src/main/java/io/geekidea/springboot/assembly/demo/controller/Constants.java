//package io.geekidea.springboot.assembly.demo.controller;
//
//import java.util.Random;
//import java.util.UUID;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//
//public class Constants {
//    // 消费者,单一线程， 进行处理业务逻辑
//    public static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
//
//    public static void main(String[] args) {
//
//        String s = UUID.randomUUID().toString().replace("-", "").toString();
//        System.out.println(s);
//
//
//    }
//}