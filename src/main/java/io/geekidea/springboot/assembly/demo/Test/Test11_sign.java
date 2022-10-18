package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.concurrent.Semaphore;

public class Test11_sign {


    private static final String TOKEN = "dHsJQs5ZXBhpBCtjGNLryQ==";
    private static final String APPID = "easyidea.jd.com";//  中文名称：微策略

    public static void main(String[] args) throws InterruptedException {

        long time = System.currentTimeMillis();
        // 加签
        String sign = DigestUtils.md5DigestAsHex((APPID + TOKEN + time).getBytes());
        System.out.println("time: " + time);
        System.out.println("sign: " + sign);
        System.out.println("appId=" + APPID + "&" + "sign=" + sign + "&" + "time=" + time + "&" + "marketId=");
    }
}
