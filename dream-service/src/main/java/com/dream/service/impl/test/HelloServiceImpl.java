package com.dream.service.impl.test;

import com.dream.service.api.test.HelloService;
import org.springframework.stereotype.Service;

@Service("test.HelloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello World";
    }
}
