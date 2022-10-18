package io.geekidea.springboot.assembly.demo.Test;


import com.example.demo.model.DateTypeEnum;
import com.example.demo.model.FeelInsightParam;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.DateUtilss;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * https://blog.csdn.net/justdoit_potato/article/details/120022084
 */
@Slf4j
public class Test32 {
    private final static String EXCEL_DIR = "/export/Data/textsearch/excel/";
    private final static String DATETIME_PATTERN = "yyyyMMddHHmmssSS";

    public static void main(String[] args) throws Exception {
//        String randomStr = UUID.randomUUID().toString().replaceAll("-", "");

//        String fileName = EXCEL_DIR + randomStr + "/" + "体验管理" + DateUtils.now(DATETIME_PATTERN) + "--" + 1 + ".xlsx";
//


        String ZIP_DIR = "E:\\export\\Data\\textsearch\\zipFile\\";
        String EXCEL_DIR = "E:\\export\\Data\\textsearch\\excel\\";
        String randomStr="123";
//        delAllFile(ZIP_DIR+randomStr);
//        delAllFile(EXCEL_DIR+randomStr);
        deleteDir(ZIP_DIR+randomStr);
        deleteDir(EXCEL_DIR+randomStr);

    }

    public static String getFileName(String randomStr, String name, Integer i) {
        StringBuffer sb = new StringBuffer();
        return sb.append(EXCEL_DIR).append(randomStr).append("/").append(name).append(i).append(".xlsx").toString();
    }



    public static void delAllFile(String path) {
        log.error("删除文件夹");
        File file = new File(path);
        String[] tempList = file.list();
        for (String s : tempList) {
            //  这里只是文件名称 不是全路径
            File temp = new File(path + "\\" + s);
            temp.delete();
        }

    }


    public static void deleteDir(String path){
        //获取目录下所有文件和目录
        File directory = new File(path);
        File files[] = directory.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                deleteDir(file.getPath());
            }else {
                file.delete();
                System.out.println(file.getName()+"：：文件已删除");
            }
        }
        //最终把该目录也删除
        directory.delete();
        System.out.println(directory.getName()+"：：目录已删除");
    }

}
