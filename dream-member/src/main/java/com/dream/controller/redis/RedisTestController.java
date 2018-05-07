package com.dream.controller.redis;

import com.dream.api.redis.JedisClient;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private JedisClient jedisClient;

    @GetMapping("/setValue")
    public String setValue() {
        if(!template.hasKey("shabao")){
            template.opsForValue().append("shabao", "我是傻宝");
            return "使用redis缓存保存数据成功";
        }else{
            template.delete("shabao");
            return "key已存在";
        }
    }

    @GetMapping("/getValue")
    public String getValue() {
        if(!template.hasKey("shabao")){
            return "key不存在，请先保存数据";
        }else{
            String shabao = template.opsForValue().get("shabao");//根据key获取缓存中的val
            return "获取到缓存中的数据：shabao="+shabao;
        }
    }

    @GetMapping("/lock")
    public Result lockTest() {
        boolean res1 = template.opsForValue().setIfAbsent("jack", "wow");
        boolean res2 = template.opsForValue().setIfAbsent("jack", "wow");
        Map<String, Boolean> result = new HashMap<>();
        result.put("res1", res1);
        result.put("res2", res2);
        return Result.success().setData(result);
    }
}
