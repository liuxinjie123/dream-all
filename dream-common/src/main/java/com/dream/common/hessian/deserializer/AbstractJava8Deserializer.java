package com.dream.common.hessian.deserializer;


import com.caucho.hessian.HessianException;
import com.caucho.hessian.io.AbstractDeserializer;
import com.caucho.hessian.io.AbstractHessianInput;

import java.io.IOException;

public abstract class AbstractJava8Deserializer extends AbstractDeserializer {

    @Override
    public abstract Class<?> getType();

    @Override
    public Object readMap(AbstractHessianInput in) throws IOException {
        int ref = in.addRef(null);

        String key = in.readString();
        if (key ==null ||"".equals(key)) {
            throw new IOException("Expected string comprising 'value'");
        }

        String value = in.readString();
        Object obj = create(value);

        in.readMapEnd();
        in.setRef(ref, obj);

        return obj;
    }

    @Override
    public Object readObject(AbstractHessianInput in, Object[] fields) throws IOException {
        String[] fieldNames = (String[]) fields;

        int ref = in.addRef(null);

        String value = null;

        for (int i = 0; i < fieldNames.length; i++) {
            String key = fieldNames[i];

            if (key.equals("value")) {
                value = in.readString();
            } else {
                in.readObject();
            }
        }

        Object obj = create(value);

        in.setRef(ref, value);

        return obj;
    }

    protected Object create(String value) throws IOException {
        if (value == null) {
            throw new IOException(getType().getName() + " expects a value.");
        }

        try {
            return stringToJodaObject(value);
        } catch (Exception e) {
            throw new HessianException(getType().getName() + ": value=" + value + "\n" + e, e);
        }
    }

    protected abstract Object stringToJodaObject(String value);
}
