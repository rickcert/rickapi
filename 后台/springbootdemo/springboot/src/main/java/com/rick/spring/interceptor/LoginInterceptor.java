package com.rick.spring.interceptor;

import com.rick.spring.constant.Constants;
import com.rick.spring.entity.HqhLoginUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * @author 小松
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断有没有Authorization这个请求头，拿到首部信息的Authorization的值
        ValueOperations ops = redisTemplate.opsForValue();
        ResponseEntity<String> res = ResponseEntity.status(202).body("Bad Credentials!");
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);
        System.out.println(token);
        String username = request.getHeader("username");

        if (token == null) {
            response.setStatus(202);
            response.getWriter().write(String.valueOf(res));
            return false;
        }
        String tokenKey = Constants.TOKEN_PREFIX + request.getHeader("username")+":"+token;
        // 3、使用token去redis中查看，有没有对应的loginUser



        HqhLoginUser hqhLoginUser = (HqhLoginUser) ops.get(tokenKey);
        if ( hqhLoginUser == null) {
            response.setStatus(202);
            response.getWriter().write(String.valueOf(res));
            return false;
        }
        // 给token续命
        redisTemplate.expire(tokenKey, Constants.TOKEN_TIME, TimeUnit.SECONDS);



        return true;
    }
}
