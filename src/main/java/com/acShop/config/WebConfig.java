package com.acShop.config;

import com.acShop.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(loginIntercepter).addPathPatterns("/**");
    }


}
