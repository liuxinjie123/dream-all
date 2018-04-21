package com.dream.rest.user;

import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/rest/user")
@RestController("rest.user.UserController")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDAO getByUserId(@PathVariable("userId") String userId) {
        return userService.getByUserId(userId);
    }
}
