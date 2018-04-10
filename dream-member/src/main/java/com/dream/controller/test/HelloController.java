package com.dream.controller.test;

import com.dream.api.test.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/world")
    public String testHelloWorld() {
        System.out.println(" --------- enter hello world --------- ");
        return helloService.hello();
    }
}
