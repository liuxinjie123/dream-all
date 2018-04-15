package com.dream.config.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * Created by hongpf on 16/4/28.
 */
public class KittHessianProxyFactory extends HessianProxyFactory {
        public   KittHessianProxyFactory(){
            super();
            this.setSerializerFactory(new KittSerializerFactory());
        }
}
