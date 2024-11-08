package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考：
 */
@Slf4j
public class Test18_lock_condition {

    private static volatile int flag;

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (flag % 3 != 0) {
                        conditionA.await();
                    }
                    flag++;
                    System.out.println("A" + i);
                    conditionB.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (flag % 3 != 1) {
                        conditionB.await();
                    }
                    flag++;
                    System.out.println("B" + i);
                    conditionC.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (flag % 3 != 2) {
                        conditionC.await();
                    }
                    flag++;
                    System.out.println("C" + i);
                    conditionA.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}




