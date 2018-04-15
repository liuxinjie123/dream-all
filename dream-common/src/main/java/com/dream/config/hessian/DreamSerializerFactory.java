package com.dream.config.hessian;

import com.caucho.hessian.io.SerializerFactory;

public class DreamSerializerFactory extends SerializerFactory {
    public DreamSerializerFactory(){
        super();
        this.addFactory(new Java8TimeSerializerFactory());
    }
}
