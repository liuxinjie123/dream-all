package com.dream.config;

import com.dream.ext.jackson.Java8TimeModule;
import com.dream.interceptor.ClientInfoMethodArgumentHandler;
import com.dream.interceptor.UserLoginHandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@ComponentScan("com.dream")
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    UserLoginHandlerInterceptor userLoginHandlerInterceptor;
    @Autowired
    ClientInfoMethodArgumentHandler clientInfoMethodArgumentHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 判断用户是否登录的拦截器
        registry.addInterceptor(userLoginHandlerInterceptor).addPathPatterns("/user/**", "/api/**", "/mall/**");
    }

    //添加自定义方法参数解析器
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(clientInfoMethodArgumentHandler);                                         //获取客户端信息
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/styles/**").addResourceLocations("file:./src-web/member/app/styles/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:./src-web/member/app/images/");
//        registry.addResourceHandler("/scripts/**").addResourceLocations("file:./src-web/member/app/scripts/");
//        registry.addResourceHandler("/files/**").addResourceLocations("file:./../files/");
    }



//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseSuffixPatternMatch(false);
//    }

    @PostConstruct
    private void jacksonConfig() {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new Java8TimeModule());
    }
}
