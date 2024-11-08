package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static String cutUrlKey(String url) {
        String[] split = StringUtils.split(url,"/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0,len = split.length;i < len; i++) {
            if (i > 1) {
                stringBuilder.append("/").append(split[i]);
            }
        }
        return stringBuilder.substring(1);
    }

    /**
     * 读取文件
     * @param fileName
     * @return     git clone https://your_username:your_token@github.com/username/your_repo.git

     * @throws IOException
     */
    //
    public static List<String>  readStringList(String  fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line : lines) {
            System.out.println(line);
        }
        return lines;
    }

    public static long  getCount(String  fileName) throws IOException {
        long lineCount = Files.lines(Paths.get("path/to/your/file.txt")).count();
        return lineCount ;
    }

//    public static List<String>  getStringList(String  fileName) throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get(fileName));
//        for (String line : lines) {
//            System.out.println(line);
//        }
//        return lines;
//    }
    
}
