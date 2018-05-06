package com.dream.controller;

import com.dream.service.KafkaSender;
import com.dream.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/kafka")
@RestController
public class KafkaController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping(value = "/send/{message}")
    public Result sendKafka(@PathVariable("message") String message) {
        try {
            logger.info(" 开始发送kafka.");
            kafkaSender.send();
            logger.info(" 发送kafka成功.");
            Thread.sleep(3000);
            return Result.success().setMsg("发送kafka成功");
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return Result.success().setMsg("发送kafka失败");
        }
    }




}
