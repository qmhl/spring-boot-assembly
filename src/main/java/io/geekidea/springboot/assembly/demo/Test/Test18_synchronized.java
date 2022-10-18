package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 参考：
 */
@Slf4j
public class Test18_synchronized {


    private static volatile int state;

    public static void main(String[] args) {
        Object o = new Object();
        //打印A
        new Thread(() -> {
            //final int num=state;
            for (int i = 0; i < 1; i++) {
                while (state != 0) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                state = (state + 1) % 3;
                System.out.print("A" + i + "     ");
                synchronized (o) {
                    o.notifyAll();
                }
            }
        }).start();
        //打印B
        new Thread(() -> {
            //final int num=state;
            for (int i = 0; i < 1; i++) {
                while (state != 1) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                state = (state + 1) % 3;
                System.out.print("B" + i + "     ");
                synchronized (o) {
                    o.notifyAll();
                }
            }
        }).start();
        //打印C
        new Thread(() -> {
            //final int num=state;
            for (int i = 0; i < 1; i++) {
                while (state != 2) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                state = (state + 1) % 3;
                System.out.println("C" + i);
                synchronized (o) {
                    o.notifyAll();
                }
            }
        }).start();


    }

}


