package com.dream.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.dream.api.dubbo.UserDubboTestService;

@Service(version = "1.0.0")
public class UserDubboTestServiceImpl implements UserDubboTestService {
    @Override
    public String helloDubbo() {
        return "Hello Dubbo";
    }
}
