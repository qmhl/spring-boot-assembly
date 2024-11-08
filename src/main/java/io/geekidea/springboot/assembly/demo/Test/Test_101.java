package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.xbp.client.XbpClient;
import io.geekidea.springboot.assembly.demo.model.DimensionOrMeasure;
import io.geekidea.springboot.assembly.demo.model.KeyVal;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class Test_101 {
    public static void main(String[] args) {

        XbpClient client = new XbpClient("", "", XbpClient.ENV.TEST);

        for (int i = 0; i <6 ; i++) {
           test(i);
            log.info("i的真实值:{}",i);
        }

        System.out.println("继续输出>>>");
    }

    public static void test(int i) {
        if(i==4){
            return;
        }else{
            log.info("i的值:{}",i);
        }
    }
}
