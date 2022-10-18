package io.geekidea.springboot.assembly.demo.Test;

import com.example.demo.model.CommonResult;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test9 {
    public static void main(String[] args) {
//        // 真正请求
//        CommonResult commonResult = new CommonResult();
//        commonResult.setId(9987);
//        commonResult.setName("手机通讯");
//        List<CommonResult> list = new ArrayList<>();
//        list.add(commonResult);
//        //        userPermissionServiceNewImpl.getFinalList(commonResults);
//        //利用list中的元素创建HashSet集合，此时set中进行了去重操作
//        HashSet set = new HashSet(list);
//        // 清空list集合
//        list.clear();
//        // 将去重后的元素重新添加到list中
//        list.addAll(set);
//        System.out.println(list);

        String format = format("2022-06-23 23:59:59", "yyyy-MM-dd");
        System.out.println(format);
        Long aLong = Long.valueOf("1");
        System.out.println(aLong);

    }

    public static String format(String date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            Date d = formatter.parse(date);
            return new SimpleDateFormat(pattern).format(d);
        } catch (Exception e) {
            return null;
        }
    }


}
