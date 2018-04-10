package com.dream.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.dream.mapper"})
public class DataBaseConfiguration {



}
