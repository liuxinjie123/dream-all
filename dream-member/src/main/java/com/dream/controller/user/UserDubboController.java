package com.dream.controller.user;

import com.dream.service.user.UserDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDubboController {
    @Autowired
    private UserDubboService userDubboService;

    @GetMapping("/user/dubbo/hello")
    public String userDubboHello() {
        return userDubboService.helloDubllo();
    }
}
