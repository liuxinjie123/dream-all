package com.dream.service;

import com.dream.config.MqConfig;
import com.dream.vo.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSConsumer;

@Component
public class MqConsumer {
    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer.class);

    @JmsListener(destination = MqConfig.TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    @SendTo(MqConfig.TOPIC_NAME_2)
    public String onTopicMessage(Object obj) {
        logger.info("接收到 topic 消息：{}", obj);
        return "接收到 topic 消息：{}" + obj;
    }

    @JmsListener(destination = MqConfig.TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    @SendTo(MqConfig.TOPIC_NAME_2)
    public String onTopicMessage2(Object obj) {
        logger.info("接收到 topic 222222222222222 消息：{}", obj);
        return "接收到 topic 222222222222222 消息：{}" + obj;
    }

    @JmsListener(destination = MqConfig.QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    @SendTo(MqConfig.QUEUE_NAME_2)
    public String onQueueMessage(Object obj) {
        logger.info("接收到 queue 消息：{}", obj);
        return "接收到 queue 消息：{}" + obj;
    }

    @JmsListener(destination = MqConfig.QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    @SendTo(MqConfig.QUEUE_NAME_2)
    public String onQueueMessage2(Object obj) {
        logger.info("接收到 queue 消息：22222{}", obj);
        return "接收到 queue 消息：22222{}" + obj;
    }

    @JmsListener(destination = "hello_activemq", containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage3(Object obj) {
        logger.info("接收到 queue 消息：333333{}", obj);
    }

    @JmsListener(destination = Constants.SECOND_KILL_PRODUCT01_QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    public void secondKillProduct01Message(Object object) {
        logger.info(" second kill product01, msg=" + object);
    }
}
