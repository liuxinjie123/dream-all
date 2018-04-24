package com.dream.service.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.api.dubbo.UserDubboTestService;
import org.springframework.stereotype.Service;

@Service
public class UserDubboService {

    @Reference(version = "1.0.0")
    UserDubboTestService userDubboTestService;

    public String helloDubllo() {
        return userDubboTestService.helloDubbo();
    }
}
