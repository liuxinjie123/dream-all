package com.dream.controller;

import com.dream.service.Producer;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@RequestMapping("/activemq")
@RestController
public class ActiveMQController {
    @Autowired
    Producer producer;
    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;


    @GetMapping("/test/{name}")
    public Result activeMQTest001(@PathVariable("name") String name) {
        for(int i=0; i<100; i++){
            producer.sendMessage(queue, "My Name is " + name + " !!!  " + i);
            producer.sendMessage(topic, "My Name is " + name + " !!!  " + i);
        }
        System.out.println(" ------------------------- end --------------------------- ");
        return Result.success();
    }
}
