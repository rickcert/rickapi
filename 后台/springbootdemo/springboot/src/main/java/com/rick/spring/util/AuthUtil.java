package com.rick.spring.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rick.spring.constant.Constants;
import com.rick.spring.entity.HqhLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AuthUtil {

    /**
     * 获取当前的登陆对象
     * @return
     */
    public static HqhLoginUser getLoginUser(RedisTemplate redisTemplate){
        ValueOperations ops = redisTemplate.opsForValue();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取首部信息的token
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);

        if (token == null) {
            log.info("当前用户未登录！");
        }
        String tokenKey = Constants.TOKEN_PREFIX + request.getHeader("username")+":"+token;

        // 3、使用token去redis中查看，有没有对应的loginUser
        return (HqhLoginUser) ops.get(tokenKey);
    }
}
