package com.dream.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.annotation.Client;
import com.dream.api.redis.JedisClient;
import com.dream.api.user.UserLoginRecordService;
import com.dream.api.user.UserService;
import com.dream.dao.user.UserDAO;
import com.dream.dao.user.UserLoginRecordDAO;
import com.dream.dto.ClientInfo;
import com.dream.threadService.LoginRecordThread;
import com.dream.utils.CookieUtils;
import com.dream.utils.SecureUtil;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Controller
public class UserLoginController {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private UserService userService;
    @Value("${indexPage}")
    private String indexPage;
    @Value("${USER_TOKEN_NAME_KEY}")
    private String USER_TOKEN_NAME;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${REDIS_USER_SESSION_INFO_KEY}")
    private String REDIS_USER_SESSION_INFO_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    @Autowired
    private ExecutorService executorService;
    @Reference(version = Constants.DUBBO_VERSION)
    private UserLoginRecordService recordService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
                            Map<String, String> map) {
        System.out.println(" login page, redirect=" + redirect);
        map.put("redirect", redirect);
        return "login";
    }

    @PostMapping(value="/login/submit")
    @ResponseBody
    public Result userLogin(@Client ClientInfo clientInfo,
                            @RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
                            HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" login, account=" + account);
        System.out.println(" login, redirect=" + redirect);
        try {
            Result<Object> result = userService.login(account, password);
            if (!Constants.CODE_SUCCESS.equals(result.getCode())) return result;
            Map<String, String> resultMap = (Map<String, String>) result.getData();
            String token = resultMap.get("token");
            String userId = resultMap.get("userId");
            // 将客户信息写入 redis
            String ip = clientInfo.getIP();
            String userAgent = clientInfo.getUserAgent();
            String acceptLanguage = clientInfo.getAcceptLanguage();
            String clientInfoMsg = ip + userAgent + acceptLanguage;
            System.out.println(" clientInfoMsg = " + clientInfoMsg);
            jedisClient.set(REDIS_USER_SESSION_INFO_KEY + ":" + token, SecureUtil.securePassword(clientInfoMsg));
            // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
            CookieUtils.setCookie(request, response, USER_TOKEN_NAME, token);
            Map<String, String> map = new HashMap<>();
            map.put("indexPage", indexPage);
            map.put("redirectPage", redirect);
            System.out.println(" login, redirect=" + redirect);
            System.out.println(" result=" + result.toString());
            result.setData(map);
            // 记录登录日志
            UserLoginRecordDAO loginRecord = new UserLoginRecordDAO(userId, account, ip, userAgent, acceptLanguage, userId, userId);
            LoginRecordThread thread = new LoginRecordThread(loginRecord, recordService);
            executorService.execute(thread);
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
