package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import io.geekidea.springboot.assembly.demo.entity.TestData;
import io.geekidea.springboot.assembly.demo.utils.ExcelMergeRowUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 * <p>
 * https://www.91mszl.com/zhangwuji/article/details/1319
 * <p>
 * https://blog.csdn.net/weixin_45559862/article/details/109889909?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-2-109889909.pc_agg_new_rank&utm_term=easyExcel%E5%8A%A8%E6%80%81%E5%90%88%E5%B9%B6%E7%9B%B8%E5%90%8C%E5%86%85%E5%AE%B9%E5%8D%95%E5%85%83%E6%A0%BC&spm=1000.2123.3001.4430
 * <p>
 * https://blog.csdn.net/qq_56233219/article/details/122730470
 * <p>
 * https://blog.csdn.net/Lzfnemo2009/article/details/125316513
 */
@Slf4j
public class Test20_easyexcel动态表头及表头合并3 {
    public static void main(String[] args) {
        String fileName = System.currentTimeMillis() + ".xlsx";
        //设置第几列开始合并
        int[] mergeColumnIndex = {0, 1};
        //设置第几行开始合并
        int mergeRowIndex = 2;
        ExcelMergeRowUtils mergeRowUtils = new ExcelMergeRowUtils(mergeRowIndex, mergeColumnIndex);
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head2()).sheet("模板")
                //行注册
                .registerWriteHandler(mergeRowUtils)
                .doWrite(getData());
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
        List<String> list1 = Arrays.asList("标准自营", "厂直", "pop-sop");
        for (int i = 1; i < 4; i++) {
            for (String s : list1) {
                List<String> head2 = new ArrayList<>();
                head2.add("差评率" + i);
                head2.add(s);
                headList.add(head2);
            }
        }
        return headList;
    }
}
