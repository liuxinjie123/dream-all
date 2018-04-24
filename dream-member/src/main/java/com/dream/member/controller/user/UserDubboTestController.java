package com.dream.member.controller.user;

import com.dream.member.service.user.UserDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDubboTestController {
    @Autowired
    private UserDubboService userDubboService;


    @GetMapping("/user/dubbo/test")
    public String helloDubbo() {
        return userDubboService.helloDubbo();
    }
}
