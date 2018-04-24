package com.dream.member.common.hessian;

import com.caucho.hessian.io.SerializerFactory;

public class DreamSerializerFactory extends SerializerFactory {
    public DreamSerializerFactory(){
        super();
        this.addFactory(new Java8TimeSerializerFactory());
    }
}
