package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.dao.SymbolSubmissionContentFailureRecordMapper;
import io.geekidea.springboot.assembly.demo.entity.SymbolSubmissionContentFailureRecord;
import io.geekidea.springboot.assembly.demo.model.ExcelData;
import io.geekidea.springboot.assembly.demo.model.RestResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/insertUpdate/")
@Slf4j
public class InsertUpdateController {
    @Autowired
    private SymbolSubmissionContentFailureRecordMapper symbolSubmissionContentFailureRecordMapper;

    @GetMapping("/test")
    public RestResponse test(Long id) throws Exception {
        log.info(" InsertUpdateController SymbolSubmissionContentFailureRecordMapper: {}",symbolSubmissionContentFailureRecordMapper);
        SymbolSubmissionContentFailureRecord build = null;
        if (id != null) {
            SymbolSubmissionContentFailureRecord  build1= SymbolSubmissionContentFailureRecord.builder().id(id).build();
            SymbolSubmissionContentFailureRecord record = symbolSubmissionContentFailureRecordMapper.selectByExample(build1);
            if(record!=null){
                record.setMessage("yyyyy");
                record.setStatus(3);
                record.setModifyTime(new Date());
                symbolSubmissionContentFailureRecordMapper.insertOrUpdate(record);
            }
        } else {
            build = SymbolSubmissionContentFailureRecord.builder()
                    .entityId("7")
                    .symbolId(11L)
                    .opType(1)
                    .message("xxxxxx")
                    .failureReason("xxxxxx")
                    .status(1)
                    .modifyTime(new Date())
                    .createTime(new Date())
                    .build();
            symbolSubmissionContentFailureRecordMapper.insertOrUpdate(build);

        }
        return RestResponse.ok();
    }

}
