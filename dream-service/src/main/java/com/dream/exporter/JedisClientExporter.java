package com.dream.exporter;

import com.dream.api.redis.JedisClient;
import com.dream.common.hessian.DreamHessianServiceExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration("api.JedisClientExporter")
public class JedisClientExporter {
	@Autowired
	private JedisClient jedisClient;

	@Bean(name = "/JedisClient")
	public HessianServiceExporter jedisService() {
		HessianServiceExporter exporter = new DreamHessianServiceExporter();
		exporter.setService(jedisClient);
		exporter.setServiceInterface(JedisClient.class);
		return exporter;
	}
}
