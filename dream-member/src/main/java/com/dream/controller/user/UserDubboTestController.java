package com.dream.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.api.user.UserDubboTestService;
import com.dream.vo.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDubboTestController {
    @Reference(version = Constants.DUBBO_VERSION)
    private UserDubboTestService userDubboTestService;

    @GetMapping("/user/dubbo/test")
    public String helloDubbo() {
        return userDubboTestService.helloDubbo();
    }
}
