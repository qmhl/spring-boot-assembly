package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_94_异常 {
    public static void main(String[] args) {
        try {
            test1(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void test1(int i) throws RuntimeException {
        if(i==1){
            throw new RuntimeException("异常1");
        }
    }

    static void test2(int i){
        if(i==1){
            throw new RuntimeException("异常1");
        }
    }


}



