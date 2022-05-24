package com.rick.spring.controller;

import com.rick.spring.annotation.Log;
import com.rick.spring.annotation.Repeat;
import com.rick.spring.entity.HqhLoginUser;
import com.rick.spring.entity.HqhUser;
import com.rick.spring.service.HqhUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小松
 */
@Slf4j
@RestController
public class IndexController {
    @Resource
    private HqhUserService userService;

    @PostMapping("login")
    @Repeat(2)
    @Log(title="登录", businessType="登录操作")
    public ResponseEntity<HqhLoginUser> login(@RequestBody @Validated HqhUser hqhUser, BindingResult bindingResult) {
        System.out.println(hqhUser.getUserName());
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        allErrors.forEach(error -> log.error("登陆时用户校验失败：{}", error.getDefaultMessage()));
        if (allErrors.size() > 0) {
            return ResponseEntity.status(500).build();
        }

        // 2、 执行登陆逻辑
        HqhLoginUser hqhLoginUser = null;
        try {
            hqhLoginUser = userService.login(hqhUser.getUserName(), hqhUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.ok().body(hqhLoginUser);
    }
    @Log(title="登出", businessType="登录操作")
    @GetMapping("logout")
    public ResponseEntity<String> logout(){
        try {
            userService.logout();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().body("退出成功");
    }

}

