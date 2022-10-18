package io.geekidea.springboot.assembly.demo.Test;

public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        get(null);
    }


    public static void get(String key) throws InterruptedException {
        int i = 0;
        String value = null;
        if (key != null) {
            System.out.println("key 不是 null");
        } else {
            i++;
            System.out.println("key wei null");
            //其他线程休息50毫秒后重试  
            Thread.sleep(50);
            get(key);
            if (i > 5) {
                return;
            }
        }
    }


}
