package com.dream.hession;

import com.dream.api.test.HelloService;
import com.dream.config.hessian.KittHessianProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class ClientApplication {
    @Value("${hessianServerIP}")
    private String hessianServerIP;

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new KittHessianProxyFactoryBean();
        factory.setServiceUrl(hessianServerIP + "HelloService");
        factory.setServiceInterface(HelloService.class);
        factory.setOverloadEnabled(true);
        return factory;
    }


}
