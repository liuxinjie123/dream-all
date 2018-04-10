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

    @GetMapping("/{userId}")
    public UserDAO getByUserId(@PathVariable("userId") String userId) {
        return userService.getByUserId(userId);
    }

    @PostMapping
    public UserDAO addUser(@RequestBody UserDAO user) {
        user.setPassword(userService.securePassword(user.getPassword()));
        return userService.addUser(user);
    }

}
