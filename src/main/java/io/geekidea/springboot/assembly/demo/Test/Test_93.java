package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.model.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_93 {
    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.setName("zhangsan");
        testBean.setAge(1);
        testBean.setList(new ArrayList<>(Arrays.asList("1","2","3")));


        List<String> list = testBean.getList();
        list.remove("1");
        System.out.println(list);
        list.add("77");
        System.out.println(JSON.toJSONString(testBean));

        String str="";
        TestBean b= JSON.parseObject(str,TestBean.class);
        System.out.println(b);
    }



}



