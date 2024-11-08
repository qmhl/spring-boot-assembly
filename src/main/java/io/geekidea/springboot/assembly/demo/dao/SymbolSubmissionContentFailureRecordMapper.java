package io.geekidea.springboot.assembly.demo.dao;


import io.geekidea.springboot.assembly.demo.entity.SymbolSubmissionContentFailureRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface SymbolSubmissionContentFailureRecordMapper {

//    int insert(SymbolSubmissionContentFailureRecord record);

    int insertOrUpdate(SymbolSubmissionContentFailureRecord record);

//    SymbolSubmissionContentFailureRecord selectByExample(SymbolSubmissionContentFailureRecord record);

    SymbolSubmissionContentFailureRecord selectByExample(SymbolSubmissionContentFailureRecord record);

}
