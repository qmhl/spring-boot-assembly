package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.dao.StrategyMapper;
import io.geekidea.springboot.assembly.demo.entity.Apple;
import io.geekidea.springboot.assembly.demo.entity.Strategy;
import io.geekidea.springboot.assembly.demo.model.RestResponse;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@RequestMapping("/test3")
@Slf4j
public class TestController3 {


    @PostMapping("/query")
    public RestResponse query(@RequestParam("id") Long id, @RequestBody List<Apple> apple) throws IOException {
        log.info(" test  >>>{}", JSON.toJSONString(apple));
        return RestResponse.ok(id + ": " + JSON.toJSONString(apple));
    }


}
