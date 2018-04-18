package com.dream.exporter;

import com.dream.api.token.UserTokenService;
import com.dream.common.hessian.DreamHessianServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration("api.UserTokenServiceExporter")
public class UserTokenServiceExporter {
	@Autowired
	private UserTokenService userTokenService;

	@Bean(name = "/UserTokenService")
	public HessianServiceExporter userTokenService() {
		HessianServiceExporter exporter = new DreamHessianServiceExporter();
		exporter.setService(userTokenService);
		exporter.setServiceInterface(UserTokenService.class);
		return exporter;
	}
}
