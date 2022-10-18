package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.xmlbeans.impl.common.ReaderInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * https://blog.csdn.net/yhld456/article/details/107055595/
 */
@Slf4j
public class Test41_生成sql {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //参数
        HashMap<String,Object> param=new HashMap<>();
        param.put("id", "撒扥");
        param.put("name", "名字");

        //原sql
        String sourceSQL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" +
                "<mapper namespace=\"customMapperUtils\">" +
                "	 <select id=\"selectData\" parameterType=\"map\" resultType=\"map\">" +
                "		select * from table where 1=1 " +
                "		<if test=\"id!=null and id!=''\">" +
                "			and id=${id}" +
                "		</if>" +
                "		<if test=\"name!=null and name!=''\">" +
                "			and name=${name}" +
                "		</if>" +
                "	 </select>" +
                "</mapper>";

        //解析
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(new ReaderInputStream(new StringReader(sourceSQL),"UTF-8"), new Configuration(), sourceSQL, new HashMap<String, XNode>());
        xmlMapperBuilder.parse();
        MappedStatement mappedStatement = xmlMapperBuilder.getConfiguration().getMappedStatement("selectData");
        String sql = mappedStatement.getBoundSql(param).getSql();


        System.out.println(sql);
    }

}
