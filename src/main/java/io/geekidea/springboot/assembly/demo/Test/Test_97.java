package io.geekidea.springboot.assembly.demo.Test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_97 {
    public static void main(String[] args) {
//        String str = "{\"uuid\":\"682984226161927113\",\"sid\":48,\"token\":\"56013f6a-ce7e-4e4f-8992-96e91dd6aad6jone20062prod\",\"abtest\":\"tndn9\",\"pin\":\"oXRk\",\"pageIdx\":1,\"project_id\":\"100317\",\"shopid\":\"1000004259\",\"streamline\":1,\"personalSwitch\":\"0\"}";
        String id= getMD5Str("京东健康APP");
        String id2= getMD5Str("京东健康app");
        System.out.println(id);
        System.out.println(id2);
    }


    /**
     * 对字符串进行加密
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        String md5 = null;
        try {
            md5 = DigestUtils.md5Hex(str);
        } catch (Exception e) {
            log.error("MD5加密出现异常：", e);
        }
        return md5;
    }

}



