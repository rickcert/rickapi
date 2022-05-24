package com.rick.spring.comon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 小松
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS","PUT")
//                .allowCredentials(true)
//                .maxAge(3600)
//                .allowedHeaders("*");
//    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
