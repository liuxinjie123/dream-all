package com.dream.member.config;

import com.dream.member.ext.jackson.Java8TimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.dream")
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    protected ObjectMapper objectMapper;

    @PostConstruct
    private void jacksonConfig() {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new Java8TimeModule());
    }


}
