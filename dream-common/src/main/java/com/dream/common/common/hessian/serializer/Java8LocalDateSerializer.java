package com.dream.member.common.hessian.serializer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Java8LocalDateSerializer extends AbstractJava8Serializer {
    @Override
    protected String objectToString(Object obj) {
        return ((LocalDate) obj).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}