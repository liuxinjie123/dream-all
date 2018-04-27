package com.dream.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.dream.api.dubbo.UserDubboTestService;
import com.dream.vo.Constants;

@Service(version = Constants.DUBBO_VERSION)
public class UserDubboTestServiceImpl implements UserDubboTestService {
    @Override
    public String helloDubbo() {
        return "Hello Dubbo";
    }
}
