package io.geekidea.springboot.assembly.demo.Test;


import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class Test48_生成file {

    private final static String FILE_DIR = "e:\\export\\App\\feel\\monitor\\";

    public static void main(String[] args) {

        String dirStr = "D:\\data111\\test";
        testCreateDir1(FILE_DIR);
    }

    public static void testCreateDir1(String dirStr) {
        //“D:\data111”目录现在不存在

        File directory = new File(dirStr);

//        //mkdir
//        boolean hasSucceeded = directory.mkdir();
//        System.out.println("创建文件夹结果（不含父文件夹）：" + hasSucceeded);

        //mkdirs
        boolean hasSucceeded = directory.mkdirs();
        System.out.println("创建文件夹结果（包含父文件夹）：" + hasSucceeded);

    }
}


