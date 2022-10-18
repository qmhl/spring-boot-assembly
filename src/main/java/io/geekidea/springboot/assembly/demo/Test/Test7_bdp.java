package io.geekidea.springboot.assembly.demo.Test;

import com.example.demo.utils.MD5Util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Test7_bdp {
    public static void main(String[] args) throws ParseException {

        String appId = "easyidea.jd.com";
        String appToken = "e6345ebc2fe9d550fd2f750e5e0da779";
        String userToken = "URM61a3772fce1d7e2c0975f7c3e36884b5";
        //MD5（appToken+userToken+time）
        long time = System.currentTimeMillis();
        // 加签
        String sign = MD5Util.getMD5Str(appToken + userToken + time);

        System.out.println("sign: "+sign);
        System.out.println("time: "+time);
    }


}
