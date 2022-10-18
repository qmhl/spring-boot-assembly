package io.geekidea.springboot.assembly.demo.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test10_cache {


    private static LoadingCache<String, String> idMappingCache = CacheBuilder.newBuilder()
            .maximumSize(1_000)                                 // 最大存储1千条
            .expireAfterAccess(1, TimeUnit.DAYS)        // 1天内未访问过的key将被回收
            .refreshAfterWrite(10, TimeUnit.MINUTES)    // 每10分钟更新一次
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    String res = null;
                    if ("hello".equals(key)) {
                        return "hello";
                    }
                    throw new NoVlaInGauvaException();

                }
            });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String nihao = idMappingCache.get("nihao");
        System.out.println(nihao);
    }


}


class NoVlaInGauvaException extends Exception {
    public NoVlaInGauvaException(String msg) {
        super(msg);
    }

    public NoVlaInGauvaException() {

    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}