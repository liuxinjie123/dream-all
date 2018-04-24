package com.dream.member.common.hessian.deserializer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Java8LocalDateDeserializer extends AbstractJava8Deserializer {
    @Override
    public Class<?> getType() {
        return LocalDate.class;
    }
    @Override
    protected Object stringToJodaObject(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
