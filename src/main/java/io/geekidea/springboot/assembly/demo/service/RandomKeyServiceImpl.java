package io.geekidea.springboot.assembly.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RandomKeyServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    public String getRandomkey() {

        List<String> keyList = new ArrayList<>();
        keyList.add("seer");
        keyList.add("yuyanjia");
        keyList.add("600");

        // 加载脚本文件
        ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("randomkey.lua"));
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptSource(scriptSource);
        // 设置脚本返回类型
        defaultRedisScript.setResultType(String.class);

        // 只用了KEYS[i] 传值，但是 args 不能为空，忘了哪个方法里注释的了，随便给个值，反正用不到
        String result = (String) redisTemplate.execute(defaultRedisScript, keyList, keyList);

        System.out.println("随机获取的randomkey是：" + result);
        return result;
    }

    public Object luaReleaseLock(String key, String value) {


        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end ";

        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(String.class);

        String res = (String) redisTemplate.execute(redisScript, Collections.singletonList(key), value);



        return res;

    }


}



