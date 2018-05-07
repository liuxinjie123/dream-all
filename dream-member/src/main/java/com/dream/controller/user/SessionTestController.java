package com.dream.controller.user;

import com.dream.dto.Session;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/session")
@RestController
public class SessionTestController {
    @Autowired
    private Session session;

    @GetMapping("/test")
    public Result sessionTestMethod001(HttpServletRequest request) {
        System.out.println("session=" + session.toString());
        System.out.println("session=" + request.getSession());
        return Result.success();
    }
}
