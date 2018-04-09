package com.dream.member.hession;

import com.dream.member.api.test.HelloService;
import com.dream.member.api.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class ClientApplication {

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://127.0.0.1:8086/HelloService");
        factory.setServiceInterface(HelloService.class);
        return factory;
    }

    @Bean
    public HessianProxyFactoryBean userClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://127.0.0.1:8086/UserService");
        factory.setServiceInterface(UserService.class);
        return factory;
    }
}
