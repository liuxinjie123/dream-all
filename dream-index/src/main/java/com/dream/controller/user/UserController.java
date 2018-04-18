package com.dream.controller.user;

import com.dream.dto.user.UserVO;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/user")
@RestController("user.UserController")
public class UserController {
    @Value("${dreamServiceAddress}")
    private String serviceAddress;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public Result getByUserId(@PathVariable("userId") String userId) {
        String url = serviceAddress + "/api/rest/user/" + userId;
        UserVO user = restTemplate.postForObject(url, null, UserVO.class);
        System.out.println("user=" + user.toString());
        System.out.println(user.getCreateTime());
        System.out.println(user.getLastUpdateTime());
        return Result.success().setData(user);
    }
}
