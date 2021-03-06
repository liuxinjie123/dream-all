package com.dream.exporter;

import com.dream.api.user.UserService;
import com.dream.common.hessian.DreamHessianServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration("api.UserServiceExporter")
public class UserServiceExporter {
    @Autowired
    private UserService userService;

    @Bean(name = "/UserService")
    public HessianServiceExporter userService() {
        HessianServiceExporter exporter = new DreamHessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }
}
