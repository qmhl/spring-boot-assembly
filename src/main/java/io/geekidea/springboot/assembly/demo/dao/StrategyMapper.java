package io.geekidea.springboot.assembly.demo.dao;


import com.example.demo.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StrategyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    Strategy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Strategy record);

    List<Strategy> selectBySelective(Strategy strategy);

    // 测试不使用resultMap
    Strategy selectTest(Strategy strategy);





}