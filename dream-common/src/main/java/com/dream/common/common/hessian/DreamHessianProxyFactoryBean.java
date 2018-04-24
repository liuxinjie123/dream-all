package com.dream.common.common.hessian;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

public class DreamHessianProxyFactoryBean extends HessianProxyFactoryBean {
    public DreamHessianProxyFactoryBean(){
        super();
        this.setProxyFactory(new DreamHessianProxyFactory());
    }
}
