package io.geekidea.springboot.assembly.demo.Test;

import com.example.demo.entity.Strategy;
import com.example.demo.model.AnalyseQueryDTO;
import com.example.demo.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.xmlbeans.impl.common.ReaderInputStream;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * https://blog.csdn.net/yhld456/article/details/107055595/
 * <p>
 * SpringBoot读取Resource下文件的几种方式
 * https://blog.csdn.net/xixiyuguang/article/details/122705049?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-122705049-blog-87785592.pc_relevant_multi_platform_featuressortv2dupreplace&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-122705049-blog-87785592.pc_relevant_multi_platform_featuressortv2dupreplace&utm_relevant_index=1
 */
@Slf4j
public class Test41_生成sql2工具类 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 参数
        Strategy strategy = new Strategy();
        strategy.setCreator("zhangqi");
        strategy.setModifier("李四");
        strategy.setType(1);

        String finalSql = getFinalSql("mapping/StrategyMapper.xml", "selectBySelective", strategy);
        log.info("===========");
        log.info("最终sql is {} ");
        log.info("{}", finalSql);
    }


    public static String getFinalSql(String resourcePath, String methodName, Object strategy) throws UnsupportedEncodingException {
        //获取原生sql
        String sourceSQL = getResourcesByStream(resourcePath);
        //解析
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(new ReaderInputStream(new StringReader(sourceSQL), "UTF-8"), new Configuration(), sourceSQL, new HashMap<String, XNode>());
        xmlMapperBuilder.parse();
        MappedStatement mappedStatement = xmlMapperBuilder.getConfiguration().getMappedStatement(methodName);
        return mappedStatement.getBoundSql(strategy).getSql();
    }


    public static String getResourcesByStream(String resourcePath) {
        String str = "";
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try {
            InputStream inputStream = resource.getInputStream();
            //将流转为字符串
            str = FileUtil.streamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }

}
