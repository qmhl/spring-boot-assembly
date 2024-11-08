package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.excel.EasyExcel;
import io.geekidea.springboot.assembly.demo.model.ExcelData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/excel/")
@Slf4j
public class ExcelController {

    @PostMapping("/read")
    public List<ExcelData> readExcel(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        try {
            return EasyExcel.read(in).head(ExcelData.class).sheet().doReadSync();
        } finally {
            in.close();
        }
    }

}
