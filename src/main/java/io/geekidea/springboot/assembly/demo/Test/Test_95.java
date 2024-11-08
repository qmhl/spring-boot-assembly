package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_95 {
    public static void main(String[] args) {
        Long time1= new Date().getTime();
        System.out.println(time1);
        Date date = new Date(time1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println(format);

        Long time2= 1713868092L;
        Date date2 = new Date(time2*1000);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = sdf2.format(date2);
        System.out.println(format2);

        String  url="http://storage.jd.local/fusion/request/202404161552112056592257108806180993.txt";
        int i = url.lastIndexOf("/");
        String fileName = url.substring(i + 1);
        System.out.println(fileName);

        List<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan",1));
        list.add(new Person("lisi",2));

        Optional.ofNullable(list).ifPresent(recordList -> {
            recordList.forEach(o -> {
                System.out.println(JSON.toJSONString(o));
            });
        });

        String contentId = "123 ";
        String styleId = " 67 ";
        Pattern p = Pattern.compile("\\d+");
        System.out.println(p.matcher(contentId).matches());
        System.out.println(p.matcher(styleId).matches());

        Integer a =678;
        Integer b =678;
        System.out.println("a==b: "+ (a==b));
        System.out.println("a equal b: "+ (a.equals(b)));

//        if (!p.matcher(contentId).matches() || !p.matcher(styleId).matches()){
//            System.out.println("error");
//        }else{
//            System.out.println("right");
//        }

    }


}



