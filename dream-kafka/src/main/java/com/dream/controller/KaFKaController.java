package com.dream.controller;

import com.dream.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class KaFKaController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/send")
    public Result sendKafka(HttpServletRequest request, HttpServletResponse response) {
        try {
            String message = request.getParameter("message");
            logger.info("kafka的消息={}", message);
            kafkaTemplate.send("test", "key", message);
            logger.info("发送kafka成功.");
            return Result.success().setMsg("发送kafka成功");
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return Result.success().setMsg("发送kafka失败");
        }
    }
}
