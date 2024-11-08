package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
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
public class Test20_easyexcel动态表头及每N行合并 {
    public static void main(String[] args) {
//       *****************************test1********************
//        String fileName = System.currentTimeMillis() + ".xlsx";
//        // 合并第一列
//        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(3, 0);
//        // 合并第二列
//        LoopMergeStrategy loopMergeStrategy2 = new LoopMergeStrategy(3, 1);
//
//        EasyExcel.write(fileName)
//                .head(head2()).sheet("模板")
//                //行注册
//                .registerWriteHandler(loopMergeStrategy)
//                .registerWriteHandler(loopMergeStrategy2)
//                .doWrite(getData());


        //       *****************************test2********************

        String fileName = System.currentTimeMillis() + ".xlsx";
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(2, 3, 1, 5);
        EasyExcel.write(fileName)
                .head(head2()).sheet("模板")
                //行注册
                .registerWriteHandler(onceAbsoluteMergeStrategy)
                .doWrite(getData());

    }

    public static List<TestData> getData() {
        List<TestData> list = new ArrayList<>();

        list.add(new TestData("当期数据", "苹果", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("当期数据", "苹果", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("当期数据", "苹果", "苹果1", "苹果1", "苹果1"));
        list.add(new TestData("当期数据1", "苹果11", "苹果111", "苹果1", "苹果111"));
        list.add(new TestData("趋势数据1", "苹果11", "苹果111", "苹果1", "苹果111"));
        list.add(new TestData("趋势数据1", "苹果11", "苹果111", "苹果1", "苹果111"));
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
