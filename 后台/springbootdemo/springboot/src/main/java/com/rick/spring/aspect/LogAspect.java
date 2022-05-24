package com.rick.spring.aspect;

import com.rick.spring.annotation.Log;
import com.rick.spring.constant.Constants;
import com.rick.spring.entity.HqhOperLog;
import com.rick.spring.service.HqhOperLogService;
import com.rick.spring.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @author itnanls(微信)
 * 我们的服务： 一路陪跑，顺利就业
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Resource
    private HqhOperLogService hqhOperLogService;

    @Resource
    private RedisTemplate redisTemplate;


    @AfterReturning("@annotation(operLog)")
    public void afterReturning(JoinPoint joinPoint, Log operLog) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        System.out.println("main----" + Thread.currentThread().getName());
        HqhOperLog hqhOperLog = createOperLog(joinPoint, request, operLog, null);
        hqhOperLogService.insert(hqhOperLog);
        log.info("{},执行了【{}】，操作",hqhOperLog.getOperName(),hqhOperLog.getTitle());
    }

    @AfterThrowing(value = "@annotation(operLog)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Log operLog, Exception exception) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HqhOperLog hqhOperLog = createOperLog(joinPoint, request, operLog, exception);
        hqhOperLogService.insert(hqhOperLog);
        log.error("{},执行了【{}】，操作",hqhOperLog.getOperName(),hqhOperLog.getTitle(),exception);
    }

    private HqhOperLog createOperLog(JoinPoint joinPoint, HttpServletRequest request, Log log, Exception exception) {
        System.out.println("log----" + Thread.currentThread().getName());
        // 1、根据现场信息，封装日志实例
        HqhOperLog hqhOperLog = new HqhOperLog();
        hqhOperLog.setTitle(log.title());
        hqhOperLog.setBusinessType(log.businessType());
        if (exception != null) {
            hqhOperLog.setErrormsg(exception.getMessage().length() > 1000 ?
                    exception.getMessage().substring(0, 1000) : exception.getMessage());
            hqhOperLog.setStatus(500);
        } else {
            hqhOperLog.setStatus(200);
        }
        // request当中获取的一些信息
        hqhOperLog.setOperIp(request.getRemoteAddr());
        hqhOperLog.setRequestMethod(request.getMethod());
        // 注意空指针的问题
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);
        System.out.println("---" + token);
        if (AuthUtil.getLoginUser(redisTemplate) != null && AuthUtil.getLoginUser(redisTemplate).getHqhUser() != null) {
            hqhOperLog.setOperName(AuthUtil.getLoginUser(redisTemplate).getHqhUser().getUserName());
        }
        else {
            hqhOperLog.setOperName("登录");
        }
        hqhOperLog.setOperUrl(request.getRequestURI());

        //获取执行的方法
        String methodName = joinPoint.getSignature().getName();
        hqhOperLog.setMethod(methodName);
        hqhOperLog.setOpertime(new Date());
        return hqhOperLog;
    }


}
