package com.dream.common.hessian;

import org.springframework.remoting.caucho.HessianServiceExporter;

public class DreamHessianServiceExporter extends HessianServiceExporter {
    public DreamHessianServiceExporter(){
        super();
        this.setSerializerFactory(new DreamSerializerFactory());
    }
}
