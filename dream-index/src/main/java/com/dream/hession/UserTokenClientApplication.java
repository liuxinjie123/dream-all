package com.dream.hession;

import com.dream.api.token.UserTokenService;
import com.dream.common.hessian.DreamHessianProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class UserTokenClientApplication {
    @Value("${hessianServerIP}")
    private String hessianServerIP;

    @Bean
    public HessianProxyFactoryBean userTokenClient() {
        HessianProxyFactoryBean factory = new DreamHessianProxyFactoryBean();
        factory.setServiceUrl(hessianServerIP + "UserTokenService");
        factory.setServiceInterface(UserTokenService.class);
        factory.setOverloadEnabled(true);
        return factory;
    }
}
