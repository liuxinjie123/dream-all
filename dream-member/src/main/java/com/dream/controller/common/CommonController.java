package com.dream.controller.common;

import com.dream.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/common")
@Controller("common.CommonController")
public class CommonController {

    @GetMapping("/hello/world")
    @ResponseBody
    public Result helloWorld() {
        return Result.success().setData("HELLO WORLD!");
    }
}
