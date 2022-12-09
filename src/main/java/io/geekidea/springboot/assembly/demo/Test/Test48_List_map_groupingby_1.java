package io.geekidea.springboot.assembly.demo.Test;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**       groupingBy 进行多字段分组 （嵌套分组）（多字段组合）
 *    https://javaforall.cn/171278.html
 *
 *    https://blog.csdn.net/ql_7256/article/details/122679902
 */
@Slf4j
public class Test48_List_map_groupingby_1 {


    public static void main(String[] args) {
        User user1 = new User("zhangsan", "beijing", 10);
        User user2 = new User("zhangsan", "beijing", 20);
        User user3 = new User("lisi", "shanghai", 30);
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Map<String, Map<String, List<User>>> collect
                = list.stream().collect(
                Collectors.groupingBy(
                        User::getAddress, Collectors.groupingBy(User::getName)
                )
        );
        System.out.println(JSON.toJSONString(collect));



        Map<String, List<User>> collect2 = list.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));
        //{zhangsan#beijing=[User{age=10, name='zhangsan', address='beijing'}, User{age=20, name='zhangsan', address='beijing'}],
        // lisi#shanghai=[User{age=30, name='lisi', address='shanghai'}]}
        System.out.println(collect2);
    }


    private static String fetchGroupKey(User user){
        return user.getName() +"#"+ user.getAddress();
    }

}


