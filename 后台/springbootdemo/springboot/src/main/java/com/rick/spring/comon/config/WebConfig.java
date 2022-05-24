package com.rick.spring.comon.config;

import com.rick.spring.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 小松
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getloginInterceptor(){
        return new LoginInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getloginInterceptor())
                .addPathPatterns("/**")
                //所有请求都被拦截包括静态资源
                .excludePathPatterns("/files/upload")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");
        //放行的请求
    }
}
