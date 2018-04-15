package com.dream.common.hessian.serializer;

import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.AbstractSerializer;

import java.io.IOException;

public abstract class AbstractJava8Serializer extends AbstractSerializer {
    @Override
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
        if (obj == null) {
            out.writeNull();
        } else {
            if (out.addRef(obj)) {
                return;
            }
            Class<?> cl = obj.getClass();

            int ref = out.writeObjectBegin(cl.getName());

            if (ref < -1) {
                out.writeString("value");
                out.writeString(objectToString(obj));
                out.writeMapEnd();
            } else {
                if (ref == -1) {
                    out.writeInt(1);
                    out.writeString("value");
                    out.writeObjectBegin(cl.getName());
                }

                out.writeString(objectToString(obj));
            }
        }
    }

    protected abstract String objectToString(Object obj);
}
