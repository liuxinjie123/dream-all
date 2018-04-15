package com.dream.config;

import com.dream.config.hessian.DreamSerializerFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;

public class KittHessianServiceExporter extends HessianServiceExporter {
    public KittHessianServiceExporter(){
        super();
        this.setSerializerFactory(new DreamSerializerFactory());
    }
}
