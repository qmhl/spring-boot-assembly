package io.geekidea.springboot.assembly.demo.Test;


import io.geekidea.springboot.assembly.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Test48_生成file {

    private final static String FILE_DIR = "e:\\export\\App\\feel\\monitor\\";

    public static void main(String[] args) {

//        String dirStr = "D:\\data111\\test";
//        testCreateDir1(FILE_DIR);

//        String  dirPath="/Users/zhangqi1092/Documents/文档/excel/";
//        FileUtil.delAllFile(dirPath);

        List<String> list = new ArrayList<>();
        list.add("/Users/zhangqi1092/Documents/文档/excel/1667008435370.xlsx");
        list.add("/Users/zhangqi1092/Documents/文档/excel/1666957820130.xlsx");
        FileUtil.removeFile(list);
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


