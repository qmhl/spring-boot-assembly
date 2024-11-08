package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Test20_down_excel2 {
    private final static String EXCEL_DIR = "src/com/example/demo/Test";

    private final static String DATETIME_PATTERN = "yyyyMMddHHmmssSS";


    public static void main(String[] args) throws Exception {

        long total = 36224;
        int size = 10000;
        int i = 1;
        // 每几页数据合并到一个excel
        int j = 5;
        // 总共生成几个excel
        int k = 0;

        // 最多下载20w下载
        if (total > 200000) {
            total = 200000;
        }

        // 总的循环次数，总共分了多少页， 也就是上面i最终的值
        int m = total % size == 0 ? (int) (total / size) : (int) (total / size + 1);

        // 生成几个excel 最终和k应该一样
        int num = m % j == 0 ? (int) (m / j) : (int) (m / j + 1);

        log.info("文本检索下载 start i={},j={},k={},m={},num={}", i, j, k, m, num);
        log.info("文本检索下载 start 应该生成 {} 个excel文件", num);

        // ======= 写文件部分：=======
        //创建excel相关内容
        String fileName = getFileName(1);
        // 创建文件夹
        createFile(fileName);
        String lastFileName = getFileName(num);


        ExcelWriter excelWriter;
        // 最后一次写入
        ExcelWriter excelWriter1;

        excelWriter = EasyExcel.write(fileName, Person.class).build();
        excelWriter1 = EasyExcel.write(lastFileName, Person.class).build();

        WriteSheet writeSheet = EasyExcel.writerSheet("文本检索数据").build();
        WriteSheet writeSheet1 = EasyExcel.writerSheet("文本检索数据").build();
        //  把filename添加到list中
        ArrayList<String> listSrc = new ArrayList<>();
        //******************特别关注start*********************
        listSrc.add(fileName);

        //******************特别关注end*********************
        while (i <= m) {
            //读取结果集数据
            List result = new ArrayList<>();
            for (int l = 0; l < 10000; l++) {
                Person person = new Person("名字" + i, 99);
                result.add(person);
            }
            //  前面每几次写入一个excel文件中
            if ((m - j * k) / j > 0) {
                log.info("第 {}次写入>>>", i);
                excelWriter.write(result, writeSheet);
            } else {
                log.info("最后一次写入>>>");
                // 最后一次写入一个excel文件
                excelWriter1.write(result, writeSheet1);
            }
            // 正好j次 开始生成一个新的excel文件
            if (i % j == 0) {
//                    log.info("恰好n次写入>>>");

                // 千万别忘记finish 会帮忙关闭流
                excelWriter.finish();
                k++;
                fileName = getFileName(k + 1);
                if (k + 1 <= num && (m - i >= j)) {
                    listSrc.add(fileName);
                    //内部版:erp
                    excelWriter = EasyExcel.write(fileName, Person.class).build();
                    writeSheet = EasyExcel.writerSheet("文本检索数据").build();
                }
            }
            if (i % j != 0 && i == m) {
//                    log.info("最后一次写入结束>>>");
                excelWriter1.finish();
                listSrc.add(lastFileName);
                k++;
            }
            i++;
        }
        log.info("文本检索下载 end i={},j={},k={},m={},num={}", i, j, k, m, num);
        log.info("文本检索下载 end 应该生成 {} 个excel文件", num);
        log.info("listSrc size is {}", listSrc.size());
        log.info("listSrc is {}", listSrc);

    }

    private static String getFileName(int i) {
        return EXCEL_DIR + "文件检索" + DateUtils.now(DATETIME_PATTERN) + "--" + i + ".xlsx";
    }

    private static File createFile(String filePath) throws Exception {
        File esfile = new File(filePath);
        File parentFile = esfile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            log.warn("创建文件夹失败！{}", parentFile.getAbsolutePath());
        }
        if (!esfile.createNewFile()) {
            log.warn("创建结果文件失败！{}", filePath);
        }
        return esfile;
    }
}
