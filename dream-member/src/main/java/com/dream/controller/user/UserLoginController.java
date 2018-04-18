package com.dream.controller.user;

import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import com.dream.utils.CookieUtils;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import com.dream.vo.user.UserLoginObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value="/login")
    @ResponseBody
    public Result userLogin(@RequestBody UserLoginObject user,
                            HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" login, user=" + user.toString());
        try {
            Result<String> result = userService.login(user.getAccount(), user.getPassword());
            if (!Constants.CODE_SUCCESS.equals(result.getCode())) return result;
            String token = result.getData();
            CookieUtils.setCookie(request, response, "USER_TOKEN", token);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(Constants.LOGIN_ERROR);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result addUser(@RequestBody UserDAO user) {
        return userService.addUser(user);
    }

    @GetMapping(value="/logout/{token}")
    public String logout(@PathVariable String token) {
        userService.logout(token); // 思路是从Redis中删除key，实际情况请和业务逻辑结合
        return "index";
    }

    @GetMapping("/token/{token}")
    @ResponseBody
    public Result getUserByToken(@PathVariable String token) {
        Result result = null;
        try {
            result = userService.queryUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.error(Constants.SYSTEM_ERROR);
        }
        return result;
    }
}
