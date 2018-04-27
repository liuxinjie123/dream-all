package com.dream.controller.user;

import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userPage() {
        return "user";
    }

    @GetMapping("/{userId}")
    public UserDAO getByUserId(@PathVariable("userId") String userId) {
        System.out.println(" ------ enter get user by userId method");
        return userService.getByUserId(userId);
    }





}
