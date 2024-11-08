package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_csv {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/zhangqi1092/Desktop/tets/contentData.csv";
        Long aLong = parseExcelFromCsvUrl0(fileName);
        System.out.println(aLong);
        System.out.println("总数："+aLong);
    }

    private static Long parseExcelFromCsvUrl0(String fileName) throws Exception {

        File tempFile = new File(fileName);
        Long total = 0L;
        try {
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String firstLine = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                // 处理每一行，这里仅打印出来
                System.out.println(line);
            }
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }

        }
        return total;
    }


    private static Map<String, Object> getContentInfoListByParseUrl(String fileName) {
        Map<String, Object> map = new HashMap<>();
        File tempFile = new File(fileName);
        Long total = 0L;

        List<List<String>> list = new ArrayList<>();
        try {
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
                String firstLine = reader.readLine(); // 读取第一行但不处理
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理每一行，这里仅打印出来
                    List<String> list1 = StringUtil.split(line, ",");
                    list.add(list1);
                    total++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            map.put("total", total);
            map.put("list", list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tempFile.delete();
        }
        return map;
    }

}



