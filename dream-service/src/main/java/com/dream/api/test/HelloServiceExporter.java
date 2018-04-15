package com.dream.api.test;

import com.dream.common.hessian.DreamHessianServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration("api.HelloServiceExporter")
public class HelloServiceExporter {
    @Autowired
    private HelloService helloService;

    @Bean(name = "/HelloService")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new DreamHessianServiceExporter();
        exporter.setService(helloService);
        exporter.setServiceInterface(HelloService.class);
        return exporter;
    }
}
