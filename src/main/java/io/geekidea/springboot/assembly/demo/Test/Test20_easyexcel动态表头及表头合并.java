package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test20_easyexcel动态表头及表头合并 {
    public static void main(String[] args) {
        String fileName = System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head()).sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(getData());
        //        EasyExcel.write(fileName).head(headList).sheet("模板").doWrite(dataList);

    }

    public static List<List<String>> getData(){
        List<List<String>> total=new ArrayList<>();
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("我是第"+i+"列!");
        }
        total.add(list);

        return total;

    }

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add("Id" );

        List<String> head1 = new ArrayList<>();
        head1.add("名称");

        List<String> head2 = new ArrayList<>();
        head2.add("尺寸");


        list.add(head0);
        list.add(head1);
        list.add(head2);

        List<String> productHead= new ArrayList<>();
        productHead.add("aaa");
        productHead.add("bbb");
        productHead.add("ccc");

        List<String> head3 = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            for (String s : productHead) {
                head3 = new ArrayList<>();
                head3.add("子产品"+i);
                head3.add(s);
                list.add(head3);
            }
        }

        return list;
    }

}
