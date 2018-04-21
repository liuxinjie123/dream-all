package com.dream.controller.user;

import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import com.dream.utils.CookieUtils;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;
    @Value("${indexPage}")
    private String indexPage;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
                            Map<String, String> map) {
        System.out.println(" login page, redirect=" + redirect);
        map.put("redirect", redirect);
        return "login";
    }

    @PostMapping(value="/login/submit")
    @ResponseBody
    public Result userLogin(@RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
                            HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" login, account=" + account);
        System.out.println(" login, redirect=" + redirect);
        try {
            Result<Object> result = userService.login(account, password);
            if (!Constants.CODE_SUCCESS.equals(result.getCode())) return result;
            String token = (String) result.getData();
            CookieUtils.setCookie(request, response, "USER_TOKEN", token);
            Map<String, String> map = new HashMap<>();
            map.put("indexPage", indexPage);
            map.put("redirectPage", redirect);
            System.out.println(" login, redirect=" + redirect);
            System.out.println(" result=" + result.toString());
            result.setData(map);
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
