package com.acShop.config;

import com.acShop.interceptor.LoginInterceptor;
import com.acShop.interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginIntercepter;

    @Autowired
    private RateLimitInterceptor rateLimitIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/payments/webhook",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                );
        registry.addInterceptor(rateLimitIntercepter)
                .addPathPatterns("/login");
    }


}
