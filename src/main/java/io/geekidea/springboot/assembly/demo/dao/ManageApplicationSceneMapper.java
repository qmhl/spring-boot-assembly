package io.geekidea.springboot.assembly.demo.dao;


import io.geekidea.springboot.assembly.demo.entity.ManageApplicationScene;
import io.geekidea.springboot.assembly.demo.entity.SymbolSubmissionContentFailureRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageApplicationSceneMapper {

    ManageApplicationScene  selectById(@Param("id") Long id);

    int insert(ManageApplicationScene scene);


    List<ManageApplicationScene> select();

    Long selectIdByNameAndLevelAndPid(@Param("name") String name, @Param("level") int level, @Param("pid") Long pid,@Param("md5") String md5);


//    ManageApplicationScene> selectByQuery;

}
