package com.dream.index.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

@Configuration
@ComponentScan({"com.dream.index"})
public class MvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    protected ObjectMapper objectMapper;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bower_components/**").addResourceLocations("file:./src-web/bower_components/");
        registry.addResourceHandler("/styles/**").addResourceLocations("file:./src-web/app/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:./src-web/app/images/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("file:./src-web/app/scripts/");
        registry.addResourceHandler("/files/**").addResourceLocations("file:./files/");
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
