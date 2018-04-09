package com.dream.member.controller.user;

import com.dream.member.api.user.UserService;
import com.dream.member.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDTO getByUserId(@PathVariable("userId") String userId) {
        return userService.getByUserId(userId);
    }

}
