package com.dream.common.hessian.deserializer;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Java8LocalDateTimeDeserializer extends AbstractJava8Deserializer {
    @Override
    public Class<?> getType() {
        return LocalDateTime.class;
    }
    @Override
    protected Object stringToJodaObject(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
