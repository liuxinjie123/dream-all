package com.dream.ext.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Java8TimeModule extends SimpleModule {
    public Java8TimeModule() {
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.addSerializer(LocalDate.class, new LocalDateSerializer());
        this.addSerializer(Date.class, new DateTimeSerializer());

        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        this.addDeserializer(Date.class, new DateTimeDeserializer());
    }

}
