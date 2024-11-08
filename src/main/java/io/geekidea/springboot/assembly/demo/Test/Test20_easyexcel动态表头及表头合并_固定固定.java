package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.entity.TestData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 * <p>
 * https://www.91mszl.com/zhangwuji/article/details/1319
 */
@Slf4j
public class Test20_easyexcel动态表头及表头合并_固定固定 {
    public static void main(String[] args) {
        String fileName = System.currentTimeMillis() + ".xlsx";

        //设置第几列开始合并
        int[] mergeColumnIndex = {0, 1};
        //设置第几行开始合并
        int mergeRowIndex = 5;

        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(mergeRowIndex, 0);

        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head2()).sheet("模板")
                //行注册
                .registerWriteHandler(loopMergeStrategy)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(getData());
        //        EasyExcel.write(fileName).head(headList).sheet("模板").doWrite(dataList);

    }

    public static List<TestData> getData() {
        List<TestData> list = new ArrayList<>();

        list.add(new TestData("当期数据", "苹果11", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("当期数据", "苹果22", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("当期数据", "苹果33", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据1", "苹果44", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据1", "苹果55", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据2", "苹果66", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据2", "苹果66", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据2", "苹果77", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("趋势数据2", "苹果88", "苹果1", "苹果1", "苹果1"));
        return list;

    }

    /**
     * 第二种设置表头的方式
     *
     * @return
     */
    private static List<List<String>> head2() {
        List<List<String>> headList = new ArrayList<List<String>>();
        headList.add(Lists.newArrayList("指标名称", "业务模式"));
        headList.add(Lists.newArrayList("指标名称", "业务模式"));
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc");
        for (int i = 1; i < 2; i++) {
            for (String s : list1) {
                List<String> head2 = new ArrayList<>();
                head2.add("子产品" + i);
                head2.add(s);
                headList.add(head2);
            }
        }
        return headList;
    }
}
