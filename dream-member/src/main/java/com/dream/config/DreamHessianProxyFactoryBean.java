package com.dream.config;

import com.dream.config.hessian.DreamHessianProxyFactory;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

public class DreamHessianProxyFactoryBean extends HessianProxyFactoryBean {
    public DreamHessianProxyFactoryBean(){
        super();
        this.setProxyFactory(new DreamHessianProxyFactory());
    }
}
