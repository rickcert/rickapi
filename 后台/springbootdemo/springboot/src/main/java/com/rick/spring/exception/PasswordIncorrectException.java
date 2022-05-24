package com.rick.spring.exception;


import com.rick.spring.annotation.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 小松
 */
@Slf4j
public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException(String message) {
        print(message);

    }
    @Log(title="登陆失败,密码错误", businessType="登录操作")
    public void print(String message)
    {
        log.info(message);
    }
}
