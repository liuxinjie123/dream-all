package com.dream.mqService;

import com.alibaba.dubbo.common.json.JSONObject;
import com.dream.config.MqConfig;
import com.dream.dao.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String onQueueMessage(String text) {
        logger.info("接收到 queue 消息：{}", text);
        UserDAO user = com.alibaba.fastjson.JSONObject.parseObject(text, UserDAO.class);
        System.out.println("user=" + user.toString());
        return "接收到 queue 消息：{}" + text;
    }

    @JmsListener(destination = MqConfig.QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    @SendTo(MqConfig.QUEUE_NAME_2)
    public String onQueueMessage2(String text) {
        UserDAO user = com.alibaba.fastjson.JSONObject.parseObject(text, UserDAO.class);
        System.out.println("user=" + user.toString());
        logger.info("接收到 queue 消息：22222{}", text);
        return "接收到 queue 消息：22222{}" + text;
    }
}
