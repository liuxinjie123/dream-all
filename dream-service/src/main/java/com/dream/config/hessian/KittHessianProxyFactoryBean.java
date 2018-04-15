package com.dream.config.hessian;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by hongpf on 16/4/28.
 */
public class KittHessianProxyFactoryBean extends HessianProxyFactoryBean {
    public KittHessianProxyFactoryBean(){
        super();
        this.setProxyFactory(new KittHessianProxyFactory());
    }
}
