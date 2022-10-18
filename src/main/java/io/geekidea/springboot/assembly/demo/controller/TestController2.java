package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import io.geekidea.springboot.assembly.demo.dao.StrategyMapper;
import io.geekidea.springboot.assembly.demo.entity.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@RequestMapping("/test2")
@Slf4j
public class TestController2 {
    @Autowired
    private StrategyMapper strategyMapper;


    @RequestMapping("/download")
    public void test0(HttpServletResponse response) throws IOException {
        downloadAnalyze(response);
    }


    public void downloadAnalyze(HttpServletResponse response) throws IOException {
        String fileName = "体验分析.zip";
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename*=" + URLEncoder.encode(fileName, "UTF-8"));

        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {

            int total = 5;
            log.info("voc detail down_total：{}", total);

            //创建excel相关内容
            String name = "体验分析--";
            int pageSize = 1;// 每页5w条
            int pageNum = 1;// 第一页
            // 总的循环次数，总共分了多少页， 也就是上面i最终的值
            int m = total % pageSize == 0 ? (int) (total / pageSize) : (int) (total / pageSize + 1);
            int i = 1;
            //  只下载50w数据
            if (m > 10) {
                m = 10;
            }
            while (i <= m) {
                //读取结果集数据--体验分析

                Strategy strategy = new Strategy();
                List<Strategy> result = strategyMapper.selectBySelective(strategy);
                if (CollectionUtils.isEmpty(result)) {
                    break;
                }
                ExcelWriter excelWriter = EasyExcel.write().excelType(ExcelTypeEnum.XLSX).build();
                //构建一个sheet页
                WriteSheet writeSheet = EasyExcel.writerSheet("体验分析数据").build();
                //构建excel表头信息
                WriteTable writeTable0 = EasyExcel.writerTable(0).head(Strategy.class).needHead(Boolean.TRUE).build();
                excelWriter.write(result, writeSheet, writeTable0);

                StringBuilder sb = new StringBuilder();
                sb.append(name);
                sb.append(pageNum);
                sb.append(".xlsx");
                ZipEntry zipEntry = new ZipEntry(sb.toString());
                zipOutputStream.putNextEntry(zipEntry);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Workbook workbook = excelWriter.writeContext().writeWorkbookHolder().getWorkbook();
                log.info("开始>>>>");
                //将excel对象以流的形式写入压缩流
                workbook.write(bos);
                bos.writeTo(zipOutputStream);
//                excelWriter.finish();
                bos.close();
                zipOutputStream.closeEntry();
                excelWriter.finish();

//
                i++;
                pageNum++;
                log.info("i is {} pageNum is {}", i, pageNum);
            }
            zipOutputStream.flush();
            log.info("voc detail down pageNum={},m={}", pageNum, m);
        } catch (Exception e) {
            log.error("voc detail down_e：", e);
        } finally {
            //关闭数据流，注意关闭的顺序[先大后小]
            zipOutputStream.close();
            outputStream.close();
        }

    }


}
