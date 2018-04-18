package com.dream.config;

import com.dream.ext.jackson.Java8TimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.PostConstruct;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    protected ObjectMapper objectMapper;

    @PostConstruct
    private void jacksonConfig() {
        objectMapper.registerModule(new Java8TimeModule());
    }


}
