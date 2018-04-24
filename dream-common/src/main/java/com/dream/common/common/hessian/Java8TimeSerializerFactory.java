package com.dream.member.common.hessian;

import com.caucho.hessian.io.*;
import com.dream.member.common.hessian.deserializer.Java8LocalDateDeserializer;
import com.dream.member.common.hessian.deserializer.Java8LocalDateTimeDeserializer;
import com.dream.member.common.hessian.serializer.Java8LocalDateSerializer;
import com.dream.member.common.hessian.serializer.Java8LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Java8TimeSerializerFactory extends AbstractSerializerFactory {
    private static final Map<Class<?>, Serializer> Serializers = new HashMap<Class<?>, Serializer>();
    private static final Map<Class<?>, Deserializer> Deserializers = new HashMap<Class<?>, Deserializer>();

    static {
        Serializers.put(LocalDateTime.class, new Java8LocalDateTimeSerializer());
        Deserializers.put(LocalDateTime.class, new Java8LocalDateTimeDeserializer());
        Serializers.put(LocalDate.class, new Java8LocalDateSerializer());
        Deserializers.put(LocalDate.class, new Java8LocalDateDeserializer());
        Serializers.put(java.math.BigDecimal.class, new StringValueSerializer());
        Deserializers.put(java.math.BigDecimal.class, new BigDecimalDeserializer());
    }

    @Override
    public Serializer getSerializer(final Class a_class) throws HessianProtocolException {
        return Serializers.get(a_class);
    }

    @Override
    public Deserializer getDeserializer(final Class a_class) throws HessianProtocolException {
        return Deserializers.get(a_class);
    }
}