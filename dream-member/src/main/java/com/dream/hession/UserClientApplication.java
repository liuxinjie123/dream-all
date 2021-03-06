package com.dream.hession;

import com.dream.api.user.UserService;
import com.dream.common.hessian.DreamHessianProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class UserClientApplication {
    @Value("${hessianServerIP}")
    private String hessianServerIP;

    @Bean
    public HessianProxyFactoryBean userClient() {
        HessianProxyFactoryBean factory = new DreamHessianProxyFactoryBean();
        factory.setServiceUrl(hessianServerIP + "UserService");
        factory.setServiceInterface(UserService.class);
        factory.setOverloadEnabled(true);
        return factory;
    }
}
