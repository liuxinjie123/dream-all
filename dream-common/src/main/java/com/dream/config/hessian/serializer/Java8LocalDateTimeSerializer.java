package com.dream.config.hessian.serializer;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Java8LocalDateTimeSerializer extends AbstractJava8Serializer {

    @Override
    protected String objectToString(Object obj) {
        return ((LocalDateTime) obj).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}