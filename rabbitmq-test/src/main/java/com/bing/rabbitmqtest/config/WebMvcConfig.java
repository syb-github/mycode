package com.bing.rabbitmqtest.config;

import com.bing.rabbitmqtest.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加拦截器
 * @author sunyibing
 * @date 2024/3/25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry, CorsRegistry corsRegistry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/**")
                // 放行登录接口
                .excludePathPatterns("/login/**")
                .order(1);
        // 允许跨域
//        corsRegistry.addMapping("/mini-login/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST")
//                .allowCredentials(true).maxAge(3600);
    }
}
