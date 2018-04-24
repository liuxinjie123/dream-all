package com.dream.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds session超时时间,单位秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
public class RedisSessionConfig {
}
