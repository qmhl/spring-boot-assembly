package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.geekidea.springboot.assembly.demo.model.DimensionOrMeasure;
import io.geekidea.springboot.assembly.demo.model.KeyVal;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
public class Test_100_柱状优化后 {
    public static void main(String[] args) {
        String jsonString = "[ [ { \"label\": \"a\", \"value\": 1 }, { \"label\": \"b\", \"value\": 2 }, { \"label\": \"c\", \"value\": 3 } ], [ { \"label\": \"a\", \"value\": 4 }, { \"label\": \"b\", \"value\": 5 }, { \"label\": \"d\", \"value\": 6 } ] ]";

        // 解析 JSON 字符串为 List<List<KeyVal>>>
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<List<KeyVal>> parsedJson = new ArrayList<>();
        for (Object obj : jsonArray) {
            List<KeyVal> innerList = new ArrayList<>();
            JSONArray innerJsonArray = (JSONArray) obj;
            for (Object innerObj : innerJsonArray) {
                JSONObject innerJsonObject = (JSONObject) innerObj;
                KeyVal keyVal = new KeyVal();
                keyVal.setLabel((String) innerJsonObject.get("label"));
                keyVal.setValue(innerJsonObject.get("value"));
                innerList.add(keyVal);
            }
            parsedJson.add(innerList);
        }

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

        // 确保每个标签都有一个对应的对象，并将缺失的值填充为 0
        List<String> allLabels = new ArrayList<>();
        for (List<KeyVal> innerList : parsedJson) {
            for (KeyVal keyVal : innerList) {
                allLabels.add(keyVal.getLabel());
            }
        }
        allLabels = new ArrayList<>(new HashSet<>(allLabels)); // 去重
        for (String label : allLabels) {
            if (!measuresMap.containsKey(label)) {
                measuresMap.put(label, new ArrayList<>());
                for (int i = 0; i < parsedJson.size(); i++) {
                    measuresMap.get(label).add(0);
                }
            } else if (measuresMap.get(label).size() < parsedJson.size()) {
                measuresMap.get(label).addAll(Collections.nCopies(parsedJson.size() - measuresMap.get(label).size(), 0));
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
        JSONArray resultJsonArray = new JSONArray();
        for (DimensionOrMeasure dimensionOrMeasure : measuresList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", dimensionOrMeasure.getData().get(0));
            jsonObject.put("data", dimensionOrMeasure.getData());
            resultJsonArray.add(jsonObject);
        }
        String resultJson = resultJsonArray.toJSONString();
        System.out.println(resultJson);
    }
}
