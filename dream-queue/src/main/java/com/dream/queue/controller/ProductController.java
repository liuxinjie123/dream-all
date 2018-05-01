package com.dream.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@RestController
public class ProductController {
    //新版本的jsmTemplate同时支持queue和topic发送
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/sendMsg")
    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    @RequestMapping("/sendTopic")
    public void sendTopic(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }

}
