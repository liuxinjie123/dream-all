package com.dream.config.hessian;

import com.caucho.hessian.io.SerializerFactory;

/**
 * Created by hongpf on 16/4/28.
 */
public class KittSerializerFactory extends SerializerFactory {
    public KittSerializerFactory(){
        super();
        this.addFactory(new Java8TimeSerializerFactory());
    }
}
