package com.dream.controller.user;

import com.dream.dto.user.UserVO;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/user")
@Controller
public class UserController {
    @Value("${dreamServiceAddress}")
    private String serviceAddress;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    @ResponseBody
    public Result getByUserId(@PathVariable("userId") String userId) {
        String url = serviceAddress + "/api/rest/user/" + userId;
        UserVO user = restTemplate.postForObject(url, null, UserVO.class);
        System.out.println("user=" + user.toString());
        System.out.println(user.getCreateTime());
        System.out.println(user.getLastUpdateTime());
        return Result.success().setData(user);
    }

    @PostMapping("/add")
    public String userPage() {
        return "user/user";
    }

}
