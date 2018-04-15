package com.dream.config.hessian;

import com.caucho.hessian.io.SerializerFactory;

public class KittSerializerFactory extends SerializerFactory {
    public KittSerializerFactory(){
        super();
        this.addFactory(new Java8TimeSerializerFactory());
    }
}
