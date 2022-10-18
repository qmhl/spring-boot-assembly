package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
public class Test40 {
    public static final String LOG_PREFIX = "test";
    /**
     * 执行查询并保存结果
     * 新增id2pin的判断逻辑
     * @param file 结果文件
     * @throws IOException
     */
    private void execQueryAndSaveResult(File file, String sql) throws IOException {
        log.info("### {}, sql:{}", LOG_PREFIX, sql);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        long startTime = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int n = 0;
//            connection = clickhouseDao.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pin = resultSet.getString(1);
                out.write(pin);
                out.newLine();
                n++;
            }
//写文件
            out.flush();
            log.info("### {},匹配条件总条数{}, 文件:{}", LOG_PREFIX, n, file.getAbsolutePath());
        } catch (IOException e) {
            log.error("### {},文件写入失败!{}, 文件：{}", LOG_PREFIX, ExceptionUtils.getStackTrace(e), file.getAbsolutePath());
            throw e;
        } catch (Exception se ) {
            log.error("### {}, clickhouse处理失败,{}", LOG_PREFIX, ExceptionUtils.getStackTrace(se));
        } finally{
            long queryTime = System.currentTimeMillis();
            long querySeconds = (queryTime - startTime) / 1000;
            log.info("### 挖包耗时:{}s", querySeconds);
//            clickhouseDao.closeAll(resultSet, preparedStatement, connection);
            try {
                out.close();
            } catch (IOException e) {
                log.error("### {}, 结果文件关闭失败！{}",LOG_PREFIX, ExceptionUtils.getStackTrace(e));

            }
        }
    }
}
