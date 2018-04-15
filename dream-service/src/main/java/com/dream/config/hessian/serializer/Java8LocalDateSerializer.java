package com.dream.config.hessian.serializer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import org.joda.time.LocalDateTime;

/**
 * Serializes LocalDateTime objects in the Joda Time API.
 *
 */
public class Java8LocalDateSerializer extends AbstractJava8Serializer {

    @Override
    protected String objectToString(Object obj) {
        return ((LocalDate) obj).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}