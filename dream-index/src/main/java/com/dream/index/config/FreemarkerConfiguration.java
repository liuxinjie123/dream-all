package com.dream.member.config;

import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@AutoConfigureAfter({FreeMarkerAutoConfiguration.class})
public class FreemarkerConfiguration {
    @Autowired
    protected freemarker.template.Configuration configuration;

    @PostConstruct
    void FreeMarkerConfigurer() throws TemplateModelException, IOException {
        configuration.setSharedVariable("staticPath","/static/");
        configuration.setSharedVariable("title","DREAM");
    }
}



