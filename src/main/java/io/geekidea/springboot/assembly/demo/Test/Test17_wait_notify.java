package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test17_wait_notify {




    public static final Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            //同步代码块
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + "          T1 start ");
                try {
                    System.out.println(System.currentTimeMillis() + "          T1  wait for  ");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "          T1 end ");

            }
        }
    }


    public static class T2 extends Thread {
        @Override
        public void run() {
            //同步代码块
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + "          T2 start ");
                object.notify();
                System.out.println(System.currentTimeMillis() + "          T2  end  ");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
        // 执行结果 ：调用wait（）进入等待状态；notify（）通知wait()的代码块，
        // 但是等notify所在的代码块执行完之后，（因为使用synchronized 锁）才能执行wait()代码块后面的代码；
        //1650272254082          T1 start
        //1650272254082          T1  wait for
        //1650272254082          T2 start
        //1650272254082          T2  end
        //1650272256096          T1 end

    }
}