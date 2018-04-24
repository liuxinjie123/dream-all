package com.dream.member.ext.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDeserializer extends JsonDeserializer<Date> {
    protected static final SimpleDateFormat dateTimeformatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String str = jp.getText().trim();
        if (str.length() == 0) return null;
        try {
            return dateTimeformatter.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

}
