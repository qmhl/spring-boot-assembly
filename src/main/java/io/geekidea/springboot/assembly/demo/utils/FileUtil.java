package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class FileUtil {


    public static String streamToString(InputStream inputStream) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null) {
                builder.append(s);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 删除文件夹下所有的文件
     *
     * @param path
     */
    public static void delAllFile(String path) {
        log.error("删除文件夹");
        File file = new File(path);
        String[] tempList = file.list();
        log.info("tempList  ->{}", Arrays.asList(tempList));
        for (String s : tempList) {
            //  这里只是文件名称 不是全路径
            File temp = new File(path + "//" + s);
            log.info("delAllFile  ->{}", temp.getPath());
            temp.delete();
        }

    }

    /**
     * 删除某个文件
     *
     * @param filePaths 多个文件路径
     */
    public static void removeFile(List<String> filePaths) {
        for (String filePath : filePaths) {
            if (filePath != null) {
                File f = new File(filePath);
                // 如果文件路径只有单个文件
                if (f.exists() && f.isFile()) {
                    f.delete();
                }
            }
        }
    }
}
