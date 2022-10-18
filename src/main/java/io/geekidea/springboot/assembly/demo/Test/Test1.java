package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {

    public static void main(String[] args) {

        ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        System.out.println(list1.size());
        System.out.println(list1.subList(0, 2).toString());

//        ========================
        ArrayList<Integer> list2 = Lists.newArrayList(11,89,46,4,1, 2, 3, 4);
        Boolean flag = false;
        while (!flag) {
            if (list2.contains(3)) {
                System.out.println("包含");
                flag = true;
            }
            System.out.println("内部");
        }
        System.out.println("结束了");

        System.out.println("=="+list2.remove(1));
        System.out.println(list2.size());

        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));
        System.out.println("随机数"+new Random().nextInt(list2.size()));

        String str="{\"common_info\":{\"scene\":\"center\",\"scope_key\":\"default\",\"pool_config\":[]},\"recall_config_define\":[{\"name\":\"主站用户偏好类目榜单\",\"type\":\"common\"},{\"name\":\"主站相关类目榜单\",\"type\":\"common\"},{\"name\":\"7Fresh相关类目榜单\",\"type\":\"common\"},{\"name\":\"热销榜单\",\"type\":\"common\"}],\"recall_config_select\":[{\"name\":\"主站用户偏好类目榜单\",\"num\":20,\"weight\":0.6},{\"num\":20,\"weight\":0.4,\"name\":\"主站相关类目榜单\"}],\"merge_list_config\":{\"cross_config\":{\"type\":\"cate\",\"field\":\"tags\",\"limit\":1},\"filter_config\":{\"type\":[]},\"final_num\":{\"condition\":\"eq\",\"value\":1}}}";
    }
}
