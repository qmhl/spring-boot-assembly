package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.geekidea.springboot.assembly.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/csv/")
@Slf4j
public class CSVController {

    @PostMapping("/read")
    public List<ExcelData> readExcel(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        try {
            return EasyExcel.read(in).head(ExcelData.class).sheet().doReadSync();
        } finally {
            in.close();
        }
    }


    /**
     * 使用opencsv  解析csv文件
     *
     * @param args
     * @throws Exception
     */
    public static void main1(String[] args) throws Exception {
        // 创建CSVReader对象
        try (CSVReader csvReader = new CSVReader(new FileReader("path/to/your/csvfile.csv"))) {
            // 读取CSV文件的所有行
            List<String[]> lines = csvReader.readAll();

            // 遍历并处理每一行
            for (String[] line : lines) {
                // 处理每一行，例如打印
                System.out.println(String.join(",", line));
            }
        }
    }

    /**
     * 使用opencsv  解析csv文件
     * <p>
     * 参考链接：
     * https://blog.csdn.net/xuan0zai/article/details/130732156  [重点是注解]
     * <p>
     * https://blog.csdn.net/xiaoxiamiqianqian/article/details/116563587?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0-116563587-blog-130732156.235^v43^pc_blog_bottom_relevance_base8&spm=1001.2101.3001.4242.1&utm_relevant_index=3
     *
     * @param args
     * @throws Exception
     */
    public static void main11(String[] args) throws Exception {
        // 使用  CsvToBeanBuilder
        List<MyObject> list = readCsv("nnnnnnnn.csv");
    }

    /**
     * 使用apace commons csv  解析csv文件
     * 参考链接：  https://blog.51cto.com/u_16213365/8327995
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String fileName="/Users/zhangqi1092/Desktop/tets/csvData.csv";
        List<List<String>> lists = readFromCommonsCSV0("/Users/zhangqi1092/Desktop/tets/csvData.csv");
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("prefix: "+ prefix);
        System.out.println(lists);
//        List<User> list1 = readFromCommonsCSV("/Users/zhangqi1092/Desktop/tets/csvData.csv");
//        System.out.println(list1);
        List list2 = readFromCommonsCSV2( "/Users/zhangqi1092/Desktop/tets/csvData.csv");
        System.out.println(list2);
        long lineNum = Files.lines(Paths.get("/Users/zhangqi1092/Desktop/tets/csvData.csv")).count();
        System.out.println(lineNum);

        List<Map> maps = readFromCommonsCSVByUrl("http://storage.jd.local/fusion/request/202404271115111701560075659353050218.csv");
        System.out.println(JSON.toJSONString(maps));


    }


    private static List<MyObject> readCsv(String path) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CsvToBean<MyObject> csvToBean = new CsvToBeanBuilder<MyObject>(reader).withType(MyObject.class).withIgnoreLeadingWhiteSpace(true).build();

        return csvToBean.parse();
    }

    /**
     * 解析CSV文件
     *
     * @return List<User>
     */
    public static List<List<String>> readFromCommonsCSV0(String fileName) throws IOException {
        List<List<String>> res = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        FileReader fileReader = new FileReader(fileName);
        CSVParser csvParser = new CSVParser(fileReader, csvFormat);
        csvParser.getRecords().forEach(csvRecord -> {
            List<String> row = new ArrayList<>();
            csvRecord.forEach(row::add);
            res.add(row);
        });
        return res;
    }

    public static List<User> readFromCommonsCSV(String fileName) {
        List<User> users = new ArrayList<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        try (FileReader fileReader = new FileReader(fileName); CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {
            csvParser.getRecords().forEach(csvRecord -> {
                Integer id = Integer.valueOf(csvRecord.get(0));
                String name = csvRecord.get(1);
                String gender = csvRecord.get(2);
                User user = new User(name, gender, id);
                users.add(user);
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
        users.forEach(System.out::println);
        return users;
    }

    /**
     * 有问题 返回的对象中有null
     *
     * @return
     */
    public static List<Map> readFromCommonsCSV2(String fileName) {
        List list = new ArrayList<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        try (FileReader fileReader = new FileReader(fileName); CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {
            csvParser.getRecords().forEach(csvRecord -> {
                Map<String, String> map = csvRecord.toMap();
                list.add(map);
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
        return list;
    }

    public static List<Map> readFromCommonsCSVByUrl(String csvUrl) throws IOException {
        List list = new ArrayList<>();
        //创建CSVFormat对象
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        //初始化CSVParser 对象
        CSVParser csvParser = CSVParser.parse(new URL(csvUrl), Charset.forName("UTF-8"), format);
        csvParser.getRecords().forEach(csvRecord -> {
            Map<String, String> map = csvRecord.toMap();
            list.add(map);
        });
        return list;
    }
}