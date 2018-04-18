package com.dream.config;

import com.dream.ext.jackson.Java8TimeModule;
import com.dream.interceptor.UserLoginHandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.PostConstruct;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    UserLoginHandlerInterceptor userLoginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 判断用户是否登录的拦截器
        registry.addInterceptor(userLoginHandlerInterceptor).addPathPatterns("/user/**", "/api/**", "/mall/**");
    }

    @PostConstruct
    private void jacksonConfig() {
        objectMapper.registerModule(new Java8TimeModule());
    }


}
