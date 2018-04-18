package com.dream.hession;

import com.dream.api.redis.JedisClient;
import com.dream.common.hessian.DreamHessianProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class JedisApplication {
    @Value("${hessianServerIP}")
    private String hessianServerIP;

    @Bean
    public HessianProxyFactoryBean jedisClient() {
        HessianProxyFactoryBean factory = new DreamHessianProxyFactoryBean();
        factory.setServiceUrl(hessianServerIP + "JedisClient");
        factory.setServiceInterface(JedisClient.class);
        factory.setOverloadEnabled(true);
        return factory;
    }


}
