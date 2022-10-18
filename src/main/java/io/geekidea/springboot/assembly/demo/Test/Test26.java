package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test26 {
    private static Object Person;

    public static void main(String[] args) throws Exception {
        List<String> erpList = Arrays.asList("moxue1", "111", "111", "moxue1");
        List<List<Integer>> touchDeptList = new ArrayList<>();
        touchDeptList.add(Arrays.asList(9987));
        getErpListByTouchDept(touchDeptList, Arrays.asList("xxxx"), erpList);
        log.info("finali erpList {}", erpList);


    }

    //touchDeptList:[[1727]],leadList:["wangweicheng3","moxue1","niufei6","bjzhangmeiying","luofangwei3","changshundong","shishulin5","yuanbo49","zhouzhao7","wangyiqiao"]
    private static void getErpListByTouchDept(List<List<Integer>> touchDeptList, List<String> leadList, List<String> erpList) {
        log.info("touchDeptList:{},leadList:{}", JSON.toJSONString(touchDeptList), JSON.toJSONString(leadList));
        if (leadList.size() > 0) {
            if (touchDeptList != null && touchDeptList.size() > 0) {
                //查询完触达部门之后erp有可能重复，需要去重
                erpList = erpList.stream().distinct().collect(Collectors.toList());
                log.info("erpList is {},", erpList);
            } else {
                erpList.addAll(leadList);
            }
        }
        log.info("erpList:{}", JSON.toJSONString(erpList));
    }


}
