package io.geekidea.springboot.assembly.demo.Test;


import io.geekidea.springboot.assembly.demo.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态生成表头
 * https://blog.csdn.net/lirui874125/article/details/123546425
 */
@Slf4j
public class Test22 {
    private static Object Person;

    public static void main(String[] args) throws Exception {
//        List<Double> resList = Lists.newArrayList();
//        Double gmvIndex=0.0d;
//        OptionalDouble gmvAverage = resList.stream().filter(Objects::nonNull).mapToDouble(Double::doubleValue).average();
//        if (gmvAverage.isPresent()) {
//             gmvIndex = gmvAverage.getAsDouble();
//        }
//        System.out.println(gmvIndex);

//        Long i = 72440582125L;
//        System.out.println(i);

        String content = "满3.90元减元";
//        //正则表达式，用于匹配非数字串，+号用于匹配出多个非数字串
//        String regEx = "[^0-9]+";
//        Pattern pattern = Pattern.compile(regEx);
//        //用定义好的正则表达式拆分字符串，把字符串中的数字留出来
//        String[] cs = pattern.split(content);
//        System.out.println(Arrays.toString(cs));

        String number = getNumber(content);
        log.info("number is {}", number);


        DecimalFormat df = new DecimalFormat("0.00%");
        System.out.println(df.format(0.021355));


        double d = 4.67d;
//        Integer f = Integer.parseInt(String.valueOf(d));
        Long g = Math.round(d);

//        System.out.println(f);
        System.out.println(g);

        List<io.geekidea.springboot.assembly.demo.model.Person> list = new ArrayList<>();

        list.add(new Person("xx",11));
        list.add(new Person("yy",22));
        list.add(new Person("zz",33));
        System.out.println(list);
        list.forEach(x->{
            if(x.getName().equals("xx")){
                x.setName("xxxxxx");
            }
            if(x.getName().equals("yy")){
                x.setName("yyyyyyyyy");
            }
        });
        System.out.println(":"+list);
        log.info("{}"+list);
    }


    public static String getNumber(String str) {
        //先判断有没有整数，如果没有整数那就肯定就没有小数
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        String result = "";
        if (m.find()) {
            Map<Integer, String> map = new TreeMap<>();
            Pattern p2 = Pattern.compile("(\\d+\\.\\d+)");
            m = p2.matcher(str);
            //遍历小数部分
            while (m.find()) {
                result = m.group(1) == null ? "" : m.group(1);
                int i = str.indexOf(result);
                String s = str.substring(i, i + result.length());
                map.put(i, s);
                //排除小数的整数部分和另一个整数相同的情况下，寻找整数位置出现错误的可能，还有就是寻找重复的小数
                // 例子中是排除第二个345.56时第一个345.56产生干扰和寻找整数345的位置时，前面的小数345.56会干扰
                str = str.substring(0, i) + str.substring(i + result.length());
            }
            //遍历整数
            Pattern p3 = Pattern.compile("(\\d+)");
            m = p3.matcher(str);
            while (m.find()) {
                result = m.group(1) == null ? "" : m.group(1);
                int i = str.indexOf(result);
                //排除jia567.23.23在第一轮过滤之后留下来的jia.23对整数23产生干扰
                if (String.valueOf(str.charAt(i - 1)).equals(".")) {
                    //将这个字符串删除
                    str = str.substring(0, i - 1) + str.substring(i + result.length());
                    continue;
                }
                String s = str.substring(i, i + result.length());
                map.put(i, s);
                str = str.substring(0, i) + str.substring(i + result.length());
            }
            result = "";
            for (Map.Entry<Integer, String> e : map.entrySet()) {
                result += e.getValue() + ",";
            }
            result = result.substring(0, result.length() - 1);
        } else {
            result = "";
        }

        System.out.println(result);
        return result;
        //String[] split = result.split(",");
        //String resultRtr = split[split.length-1];
        //return resultRtr;
    }


}
