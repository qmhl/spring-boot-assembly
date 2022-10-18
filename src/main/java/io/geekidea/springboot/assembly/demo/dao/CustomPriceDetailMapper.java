package io.geekidea.springboot.assembly.demo.dao;

import com.example.demo.model.CustomPriceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface CustomPriceDetailMapper {


    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(CustomPriceDetail record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(CustomPriceDetail record);


    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    CustomPriceDetail selectByPrimaryKey(Long id);

    CustomPriceDetail selectByProjectId(Integer projectId);

    int deleteByProjectId(Integer projectId);



}