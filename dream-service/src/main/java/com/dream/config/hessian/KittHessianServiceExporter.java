package com.dream.config.hessian;

import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Created by hongpf on 16/4/28.
 */
public class KittHessianServiceExporter extends HessianServiceExporter {
    public KittHessianServiceExporter(){
        super();
        this.setSerializerFactory(new KittSerializerFactory());
    }
}
