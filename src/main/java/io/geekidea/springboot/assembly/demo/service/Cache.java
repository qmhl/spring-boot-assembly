package io.geekidea.springboot.assembly.demo.service;

import com.example.demo.model.Entry;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Cache {


    /**
     * guava cache 缓存实体
     */
    LoadingCache<String, Entry> cache = CacheBuilder.newBuilder()
            // 缓存刷新时间
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            // 设置缓存个数
            .maximumSize(500)
            .build(new CacheLoader<String, Entry>() {
                @Override
                // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                public Entry load(String appKey) {
                    return getEntryFromDB(appKey);
                }


                // 数据库进行查询
                private Entry getEntryFromDB(String name) {
                    System.out.println("load entry info from db!entry:{}" +name);
                    return new Entry(name,"上海");
                }
            });

    /**
     * 对外暴露的方法
     * 从缓存中取entry，没取到就走数据库
     */
    public Entry getEntry(String name) throws ExecutionException {
        return cache.get(name);
    }



    @PostConstruct
    public void initCache() {
        log.info("init entry cache start!");
        //读取所有记录
        List<Entry> list = Lists.newArrayList();
        list.add(new Entry("beijing","北京"));
        list.add(new Entry("shanghai","上海"));
        list.add(new Entry("shenzhen","深圳"));

        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (Entry entry : list) {
            try {
                this.getEntry(entry.getName());
            } catch (Exception e) {
                log.error("init cache error!,e:{}", e.getMessage());
            }
        }
        log.info("init entry cache end!");
    }
}