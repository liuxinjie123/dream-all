package com.dream.service;

import com.dream.config.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSConsumer;

@Component
public class Consumer {
    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer.class);

    @JmsListener(destination = MqConfig.TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    @SendTo(MqConfig.TOPIC_NAME_2)
    public String onTopicMessage(String msg) {
        logger.info("接收到 topic 消息：{}", msg);
        return "接收到 topic 消息：{}" + msg;
    }

    @JmsListener(destination = MqConfig.QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    @SendTo(MqConfig.QUEUE_NAME_2)
    public String onQueueMessage(String msg) {
        logger.info("接收到 queue 消息：{}", msg);
        return "接收到 queue 消息：{}" + msg;
    }
}
