package com.dream.controller;

import com.dream.service.MqProducer;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.time.LocalDateTime;

@RequestMapping("/second-kill")
@RestController
public class SecondKillController {
    @Autowired
    MqProducer mqProducer;
    private volatile int count = 0;

    @PostMapping("/order")
    public Result createOrder() {
        if (count <= 10) {
            count++;
            Queue queue = new ActiveMQQueue(Constants.SECOND_KILL_PRODUCT01_QUEUE_NAME);
            mqProducer.sendMessage(queue, "second kill product01, now=" + LocalDateTime.now());
            return Result.success().setMsg(Constants.SECOND_KILL_SUCCESS);
        } else {
            return Result.error(Constants.SECOND_KILL_FAILURE);
        }
    }
}
