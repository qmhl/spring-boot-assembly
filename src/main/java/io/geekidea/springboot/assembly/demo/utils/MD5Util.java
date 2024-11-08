package io.geekidea.springboot.assembly.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.ExceptionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author sunb
 */
@Slf4j
public class MD5Util {
    /**
     * 计算本地文件的md5值
     * @param file 本地文件
     * @return 16进制的md5字符串
     */
    public static String getMD5(File file) {
        String md5 = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            md5 = DigestUtils.md5Hex(in);
        } catch(IOException e) {
            log.error(e.getMessage());
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return md5;
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