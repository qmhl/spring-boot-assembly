package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.BusinessTypeLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;


@Slf4j
public class Test43_自营pop同城购跨级 {
    //Arrays.asList(Arrays.asList("自营"), Arrays.asList("自营","厂直"),Arrays.asList("pop","popsop")
//                ,Arrays.asList("全渠道","商超"));
    public static void main(String[] args) {
        List<List<String>> commonList = Arrays.asList(Arrays.asList("自营"),Arrays.asList("全渠道"),Arrays.asList("自营","标准"));
        Map<String, List<List<String>>> map = splitList(commonList);
        log.info(JSON.toJSONString(map));
        System.out.println("=============");
//        BusinessTypeLevel businessTypeLevel = getBusinessLevel(commonList);
        BusinessTypeLevel businessTypeLevel = getBusinessLevel(map.get("pop"));
        BusinessTypeLevel businessTypeLevel2 = getBusinessLevel(map.get("allChannel"));
        System.out.println(JSON.toJSONString(businessTypeLevel));

        System.out.println("======全渠道=======");

        System.out.println(JSON.toJSONString(businessTypeLevel2));


    }


    public static BusinessTypeLevel getBusinessLevel(List<List<String>> commonList) {

        if (CollectionUtils.isEmpty(commonList)) {
            return null;
        }

        int level = 1;
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        // size :1 没跨级 都是1级  2：没跨级 都是2级 -1：跨级
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).size() == commonList.get(0).size()) {
                level = commonList.get(0).size();
            } else {
                level = -1;
            }
        }
        //跨级
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).size() == 1) {
                list1.add(commonList.get(i).get(0));
            }
            if (commonList.get(i).size() == 2) {
                list2.add(commonList.get(i).get(1));
            }
        }

        BusinessTypeLevel businessTypeLevel = new BusinessTypeLevel();
        businessTypeLevel.setLevel(level);
        businessTypeLevel.setLevel1List(list1);
        businessTypeLevel.setLevel2List(list2);
        return businessTypeLevel;
    }

    /**
     * 将入参中的业务类型分割成自营pop及 同城购两部分
     *
     * @param commonList
     * @return
     */
    public static Map<String, List<List<String>>> splitList(List<List<String>> commonList) {

        //全渠道、自营pop的List
        List<List<String>> allChannelList = new LinkedList<>();
        List<List<String>> popList = new LinkedList<>();
        for (int i = 0; i < commonList.size(); i++) {
            if (commonList.get(i).contains("全渠道")) {
                allChannelList.add(commonList.get(i));
            } else {
                popList.add(commonList.get(i));
            }

        }
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("pop", popList);
        map.put("allChannel", allChannelList);
        return map;
    }
}
