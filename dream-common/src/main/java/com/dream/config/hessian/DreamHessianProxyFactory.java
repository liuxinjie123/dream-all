package com.dream.config.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

public class DreamHessianProxyFactory extends HessianProxyFactory {
        public DreamHessianProxyFactory(){
            super();
            this.setSerializerFactory(new DreamSerializerFactory());
        }
}
