package com.dream.config;

import com.dream.ext.jackson.Java8TimeModule;
import com.dream.interceptor.UserLoginHandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan({"com.dream"})
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    UserLoginHandlerInterceptor userLoginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 判断用户是否登录的拦截器
        registry.addInterceptor(userLoginHandlerInterceptor).addPathPatterns("/user/**", "/api/**", "/mall/**");
    }

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/bower_components/**").addResourceLocations("file:./src-web/bower_components/");
//        registry.addResourceHandler("/styles/**").addResourceLocations("file:./src-web/app/styles/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:./src-web/app/images/");
//        registry.addResourceHandler("/scripts/**").addResourceLocations("file:./src-web/app/scripts/");
//        registry.addResourceHandler("/files/**").addResourceLocations("file:./files/");
//    }

    @PostConstruct
    private void jacksonConfig() {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new Java8TimeModule());
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/404", "/error/404");
//    }
//
//    @Bean(name = "validator")
//    public Validator createBeanValidator() {
//        return new LocalValidatorFactoryBean();
//    }

}
