package com.dream.service;

import com.dream.config.MqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service("producer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = MqConfig.QUEUE_NAME_2, containerFactory = "jmsListenerContainerQueue")
    public void consumerQueueMessage(String text) {
        System.out.println("从 " + MqConfig.QUEUE_NAME_2 + " 队列收到的回复报文为:" + text);
    }

    @JmsListener(destination = MqConfig.TOPIC_NAME_2, containerFactory = "jmsListenerContainerTopic")
    public void consumerTopicMessage(String text) {
        System.out.println("从 " + MqConfig.TOPIC_NAME_2 + " 队列收到的回复报文为:" + text);
    }
}
