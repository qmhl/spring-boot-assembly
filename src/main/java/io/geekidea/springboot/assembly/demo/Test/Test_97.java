package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.geekidea.springboot.assembly.demo.model.Entity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

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


        String rule = "[{\"name\":\"(\",\"type\":\"symbol\",\"leftBracket\":true},{\"id\":\"4zBWJBBmiOh\",\"name\":\"是否影分身商品\",\"nameEn\":\"shadowSkuFlag\",\"labelType\":\"枚举型\",\"value\":\"11\",\"valueDesc\":[\"等值查询11\"],\"entityId\":2,\"entityName\":\"商品\",\"operator\":\"EQUAL\"},{\"id\":\"4zBWJBBrH37\",\"name\":\"且\",\"type\":\"symbol\",\"and\":true},{\"id\":\"4zBWNi6MFo5\",\"name\":\"是否影分身商品\",\"nameEn\":\"skuShadowSkuFlag\",\"labelType\":\"枚举型\",\"value\":[\"1\",\"0\"],\"valueDesc\":[\"是\",\"否\"],\"entityId\":2,\"entityName\":\"商品\",\"operator\":\"IN\"},{\"id\":\"4zBWNi7FhRZ\",\"name\":\"且\",\"type\":\"symbol\",\"and\":true},{\"name\":\")\",\"type\":\"symbol\",\"rightBracket\":true}]";
        List<Entity> ruleItemList = JSON.parseObject(rule, new TypeReference<List<Entity>>(){});
        for (Entity item : ruleItemList) {
            System.out.println(item);
        }

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



