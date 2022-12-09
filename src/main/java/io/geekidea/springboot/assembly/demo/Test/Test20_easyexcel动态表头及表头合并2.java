package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
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
 */
@Slf4j
public class Test20_easyexcel动态表头及表头合并2 {
    public static void main(String[] args) {
        String fileName = System.currentTimeMillis() + ".xlsx";

        //设置第几列开始合并
        int[] mergeColumnIndex = {0, 1, 2, 3};
        //设置第几行开始合并
        int mergeRowIndex = 2;

        ExcelMergeRowUtils excelMergeRowUtils =  new ExcelMergeRowUtils(mergeRowIndex, mergeColumnIndex);


        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head2()).sheet("模板")
                //行注册
                .registerWriteHandler(excelMergeRowUtils)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(getData());
        //        EasyExcel.write(fileName).head(headList).sheet("模板").doWrite(dataList);

    }

    public static List<List<String>> getData() {
        List<List<String>> total = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                list.add("我是第" + j + "列!");
            }
            total.add(list);
        }


        return total;

    }

    private static List<List<String>> head1() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add("指标名称");
        head0.add("业务模式");


        List<String> head1 = new ArrayList<>();
        head1.add("指标名称");
        head1.add("业务模式");

        List<String> head2 = new ArrayList<>();
        head2.add("尺寸");
        head2.add("尺寸第二行");


        list.add(head0);
        list.add(head1);
        list.add(head2);

        List<String> productHead = new ArrayList<>();
        productHead.add("aaa");
        productHead.add("bbb");
        productHead.add("ccc");

        List<String> head3 = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            for (String s : productHead) {
                head3 = new ArrayList<>();
                head3.add("子产品" + i);
                head3.add(s);
                list.add(head3);
            }
        }
        System.out.println("list: " + list);

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
        headList.add(Lists.newArrayList("年龄", "年龄第二行"));
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc");
        for (int i = 1; i < 5; i++) {
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
