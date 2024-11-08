package io.geekidea.springboot.assembly.demo.Test;

import java.util.concurrent.Semaphore;

public class Test19_Semaphore {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphoreA=new Semaphore(1);
        Semaphore semaphoreB=new Semaphore(0);
        Semaphore semaphoreC=new Semaphore(0);
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    semaphoreA.acquire();
                    System.out.println("A"+i);
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    semaphoreB.acquire();
                    System.out.println("B"+i);
                    semaphoreC.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    semaphoreC.acquire();
                    System.out.println("C"+i);
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
