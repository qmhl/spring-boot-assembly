package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class KetamaHash {

    public static int getHash(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] keyBytes = key.getBytes();
            md5.update(keyBytes);
            byte[] digest = md5.digest();
            long hash = (long)(digest[3] & 0xFF) << 24
                    | (long)(digest[2] & 0xFF) << 16
                    | (long)(digest[1] & 0xFF) << 8
                    | digest[0] & 0xFF;
            return (int)(hash % 16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("111","222","333","444");
        list.forEach(a->{
            int hash = getHash(a);
            System.out.println("Hash value: " + hash);
        });

    }
}