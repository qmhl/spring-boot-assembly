package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.geekidea.springboot.assembly.demo.model.DimensionOrMeasure;
import io.geekidea.springboot.assembly.demo.model.KeyVal;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class Test_100_柱状优化前 {
    public static void main(String[] args) {
        String jsonString = "[ [ { \"label\": \"a\", \"value\": 1 }, { \"label\": \"b\", \"value\": 2 }, { \"label\": \"c\", \"value\": 3 } ], [ { \"label\": \"a\", \"value\": 4 }, { \"label\": \"b\", \"value\": 5 }, { \"label\": \"d\", \"value\": 6 } ] ]";
        // 解析 JSON 字符串为 List<List<Map<String, Object>>>
        List<List<KeyVal>> parsedJson = JSON.parseObject(jsonString, new TypeReference<List<List<KeyVal>>>() {
        });

        // 创建一个 Map 来存储每个标签的值列表
        Map<String, List<Object>> measuresMap = new HashMap<>();
        for (List<KeyVal> innerList : parsedJson) {
            for (KeyVal keyVal : innerList) {
                String label = keyVal.getLabel();
                Object value = keyVal.getValue();
                if (!measuresMap.containsKey(label)) {
                    measuresMap.put(label, new ArrayList<>());
                }
                measuresMap.get(label).add(value);
            }
        }

        // 创建 DimensionOrMeasure 列表
        List<DimensionOrMeasure> measuresList = new ArrayList<>();
        for (Map.Entry<String, List<Object>> entry : measuresMap.entrySet()) {
            DimensionOrMeasure dimensionOrMeasure = new DimensionOrMeasure();
            dimensionOrMeasure.setName(entry.getKey());
            dimensionOrMeasure.setData(entry.getValue());
            measuresList.add(dimensionOrMeasure);
        }

        // 将 DimensionOrMeasure 列表转换为 JSON 字符串
        String resultJson = JSON.toJSONString(measuresList);
        System.out.println(resultJson);

    }
}
