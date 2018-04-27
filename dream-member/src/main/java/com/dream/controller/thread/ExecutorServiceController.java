package com.dream.controller.thread;

import com.dream.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorServiceController {

    @GetMapping("/thread/001")
    public Result threadTest001(@RequestParam("id") String id,
                                @RequestParam("name") String name) {
        return Result.success();
    }

}
