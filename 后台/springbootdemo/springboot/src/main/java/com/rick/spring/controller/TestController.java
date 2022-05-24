package com.rick.spring.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小松
 */
@RestController
public class TestController {
    @Resource
    private  RedisTemplate redisTemplate;
    @GetMapping("test")
    public String test() {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("age",40);
        return "hqh";
    }
}
