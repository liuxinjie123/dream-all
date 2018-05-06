package com.dream.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.dao.user.UserDAO;
import com.dream.service.MqProducer;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    MqProducer mqProducer;
    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;


    @GetMapping("/log/{name}")
    public Result activeMQTest001(@PathVariable("name") String name) {
        for(int i=0; i<1; i++){
//            mqProducer.sendMessage(queue, "My Name is " + name + " !!!  " + i);
            UserDAO user = new UserDAO();
            user.setUsername("liuxinjie");
            mqProducer.sendMessage(queue, JSONObject.toJSONString(user));
            mqProducer.sendMessage(topic, "My Name is " + name + " !!!  " + i);
        }
        System.out.println(" ------------------------- end --------------------------- ");
        return Result.success();
    }
}
