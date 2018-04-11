package com.dream.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@EnableScheduling
public class ChangeTimedTaskController implements SchedulingConfigurer {
    //时间:秒，分钟，小时，日期，月份，星期，年
    @Value("${changeTaskScheduled0}")
    private String scheduledPlan0;
    @Value("${changeTaskScheduled1}")
    private String scheduledPlan1;
    @Value("${changeTaskScheduled2}")
    private String scheduledPlan2;
    @Value("${changeTaskScheduled3}")
    private String scheduledPlan3;

    private String scheduledPlan = "0 0/1 * * * *";
    //编写更改调度时间的方法
    @GetMapping("/change/{type}")
    public String changeExpression(@PathVariable("type") String type) {
        if ("1".equals(type)) {
            scheduledPlan = scheduledPlan1;
        } else if ("2".equals(type)) {
            scheduledPlan = scheduledPlan2;
        } else if ("3".equals(type)) {
            scheduledPlan = scheduledPlan3;
        } else if ("0".equals(type)) {
            scheduledPlan = scheduledPlan0;
        }
        return "SUCCESS";
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //定时任务要执行的方法
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("changeTask" + new Date());
            }
        };
        /**
         * 调度实现的时间控制
         */
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(scheduledPlan);
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        };
        scheduledTaskRegistrar.addTriggerTask(task, trigger);
    }
}
