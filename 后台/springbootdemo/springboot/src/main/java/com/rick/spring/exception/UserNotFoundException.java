package com.rick.spring.exception;


import com.rick.spring.annotation.Log;
import lombok.extern.slf4j.Slf4j;
/**
 * @author 小松
 */
@Slf4j
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
       print(message);
    }
    @Log(title="登陆失败,用户名错误", businessType="登录操作")
    public void print(String message)
    {
        log.info(message);
    }
}
