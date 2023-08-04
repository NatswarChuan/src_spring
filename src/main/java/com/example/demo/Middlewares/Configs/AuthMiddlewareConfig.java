package com.example.demo.middlewares.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.middlewares.AuthMiddleware;

/**
 * Cấu hình middleware xác thực trong ứng dụng.
 */
@Configuration
public class AuthMiddlewareConfig implements WebMvcConfigurer {
    @Autowired
    AuthMiddleware authMiddleware;

    private final String[] urls = {
        "/api/teacher","/api/teacher/"
    };

    /**
     * Thêm interceptor cho middleware xác thực và đăng ký URL áp dụng middleware.
     * 
     * @param registry Đối tượng InterceptorRegistry để đăng ký interceptor và URL áp dụng middleware.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authMiddleware).addPathPatterns(urls);
    }
}
