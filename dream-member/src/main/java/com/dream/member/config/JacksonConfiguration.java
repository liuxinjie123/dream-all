package com.dream.member.config;


import com.dream.member.ext.jackson.LocalDateDeserializer;
import com.dream.member.ext.jackson.LocalDateSerializer;
import com.dream.member.ext.jackson.LocalDateTimeDeserializer;
import com.dream.member.ext.jackson.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfiguration {
    @Bean
    public SimpleModule jacksonDateModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        return simpleModule;
    }
}
