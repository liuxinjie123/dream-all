package com.dream.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class MqConfig {
    public final static String TOPIC_NAME = "spring.boot.topic.test";
    public final static String TOPIC_NAME_2 = "spring.boot.topic.test.2";
    public final static String QUEUE_NAME = "spring.boot.queue.test";
    public final static String QUEUE_NAME_2 = "spring.boot.queue.test.2";

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(TOPIC_NAME);
    }

    /**
     * topic模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * queue模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }


}
