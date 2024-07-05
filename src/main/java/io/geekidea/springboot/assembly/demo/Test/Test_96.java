package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.entity.Parameter;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_96 {
    public static void main(String[] args) {
//        String str = "{\"uuid\":\"682984226161927113\",\"sid\":48,\"token\":\"56013f6a-ce7e-4e4f-8992-96e91dd6aad6jone20062prod\",\"abtest\":\"tndn9\",\"pin\":\"oXRk\",\"pageIdx\":1,\"project_id\":\"100317\",\"shopid\":\"1000004259\",\"streamline\":1,\"personalSwitch\":\"0\"}";
        String str = "{\"uuid\":\"682984226161927113\",\"sid\":48,\"token\":\"56013f6a-ce7e-4e4f-8992-96e91dd6aad6jone20062prod\",\"abtest\":\"tndn9\",\"pin\":\"oXRk\",\"pageIdx\":1,\"shopid\":\"1000004259\",\"streamline\":1,\"personalSwitch\":\"0\"}";
        Parameter parameter = JSON.parseObject(str, Parameter.class);
        System.out.println(checkParam(parameter));

        String id = UUID.randomUUID().toString();
        System.out.println(id);
    }


    private static boolean checkParam(Parameter parameter) {
        boolean result = parameter != null
                && parameter.getToken() != null
                && parameter.getSid() != null
                && parameter.getStreamline() != null
                && parameter.getProject_id() != null;
        if (result && parameter.getPageIdx() == null) {
            parameter.setPageIdx(1);
        }
        return result;
    }


}



