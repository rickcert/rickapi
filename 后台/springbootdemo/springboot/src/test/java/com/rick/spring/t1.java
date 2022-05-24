package com.rick.spring;

import com.rick.spring.entity.HqhUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class t1 {
    @Resource
    private  RedisTemplate redisTemplate;
    @Test
    void test1(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("name","贺启衡");
        HqhUser hqhUser = new HqhUser();
        hqhUser.setUserName("贺启衡");
        hqhUser.setPassword("122334");
        String name = (String) ops.get("name");
        System.out.println(name);
        ops.set("1",hqhUser);
        HqhUser hqh1 = (HqhUser) ops.get("1");
        System.out.println(hqh1.getUserName());
    }
}
