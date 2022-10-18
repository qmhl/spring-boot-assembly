package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.geekidea.springboot.assembly.demo.model.PersonMysql;
import io.geekidea.springboot.assembly.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.sql.*;
import java.util.Collections;

@Slf4j

public class 原生jdbc使用2 {


    private final static String EXCEL_DIR = "src/com/example/demo/Test";
    private final static String DATETIME_PATTERN = "yyyyMMddHHmmssSS";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aiwriter?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC", "root", "123456");
            Statement stmt = conn.createStatement();
            String sql = "select * from person limit 10";
            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()){
////				Object object = rs.getObject("1");
//                String id = rs.getString("uuid");
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                System.out.println(id+"  "+name+"  "+address);
//            }
            int total = 10;
            int pageSize = 4;
            // 第一页
            int pageNum = 1;
            // 总的循环次数，总共分了多少页
            int m = total % pageSize == 0 ? (int) (total / pageSize) : (int) (total / pageSize + 1);
            //  只下载50w数据
            if (m > 5) {
                m = 5;
                total = pageSize * m;
            }
            //游标数字
            int n = 0;
            //创建excel相关内容
            String fileName = getFileName(1);
            // 创建文件夹
            createFile(fileName);

            ExcelWriter excelWriter = EasyExcel.write(fileName, PersonMysql.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("测试").build();
            while (rs.next()) {

                String id = rs.getString("uuid");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                log.info("id :{}，name：{}", id, name);
                PersonMysql personMysql = new PersonMysql();
                personMysql.setName(name);
                excelWriter.write(Collections.singletonList(personMysql), writeSheet);
                n++;
                if (n == pageNum * pageSize && n < total) {
                    excelWriter.finish();
                    pageNum++;
                    excelWriter = EasyExcel.write(getFileName(pageNum), PersonMysql.class).build();
                    writeSheet = EasyExcel.writerSheet("测试").build();
                }

                if (n == total) {
                    excelWriter.finish();
                }

            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }


    }


    private static String getFileName(int i) {
        return EXCEL_DIR + "测试" + DateUtils.now(DATETIME_PATTERN) + "--" + i + ".xlsx";
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