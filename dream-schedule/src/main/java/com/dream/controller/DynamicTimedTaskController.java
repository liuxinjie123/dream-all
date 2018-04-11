package com.dream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * 利用线程池实现任务调度
 * Task任务调度器可以实现任务的调度和删除
 * 原理:
 * 实现一个类：ThreadPoolTaskScheduler线程池任务调度器，能够开启线程池进行任务调度
 * ThreadPoolTaskScheduler.schedule（）方法会创建一个定时计划ScheduleFuture,
 * 在这个方法中添加两个参数一个是Runable:线程接口类，和CronTrigger(定时任务触发器)
 * 在ScheduleFuture中有一个cancel可以停止定时任务
 *
 * @author Admin
 * <p>
 * Scheduled Task是一种轻量级的任务定时调度器，相比于Quartz,减少了很多的配置信息，但是Scheduled Task
 * 不适用于服务器集群，引文在服务器集群下会出现任务被多次调度执行的情况，因为集群的节点之间是不会共享任务信息的
 * 每个节点的定时任务都会定时执行
 */
@RequestMapping("/dynamic/task")
@RestController
@EnableScheduling
public class DynamicTimedTaskController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    @Value("${timedScheduled1}")
    private String scheduledPlan1;
    @Value("${timedScheduled2}")
    private String scheduledPlan2;
    @Value("${timedScheduled3}")
    private String scheduledPlan3;
    @Value("${timedScheduled4}")
    private String scheduledPlan4;
    @Value("${timedScheduled5}")
    private String scheduledPlan5;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 1，定义一个方法实现定时任务的启动
     * 2，定义一个方法实现用于终止定时任务
     * 3，修改定时任务时间：changeCron
     */

    /**
     * 启动定时器
     */
    @RequestMapping("/start")
    public String StartTest() {
        /**
         * task:定时任务要执行的方法
         * trigger:定时任务执行的时间
         */
        future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan1));
        System.out.println(" START TASK ");
        return "START TASK SUCCESS";
    }

    /**
     * 停止定时任务
     */
    @RequestMapping("/end")
    public String endTask() {
        if (future != null) {
            System.out.println(" future IS NOT NULL.");
            future.cancel(true);
        } else {
            System.out.println(" future IS NULL.");
        }
        System.out.println(" END TASK ");
        return "END TASK SUCCESS";
    }

    /**
     * 改变调度的时间
     * 步骤：
     * 1,先停止定时器
     * 2,在启动定时器
     */
    @RequestMapping("/change/{type}")
    public String changeTask(@PathVariable("type") String type) {
        //停止定时器
        endTask();
        //定义新的执行时间
        if ("1".equals(type)) {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan1));
        } else if ("2".equals(type)) {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan2));
        } else if ("3".equals(type)) {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan3));
        } else if ("4".equals(type)) {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan4));
        } else if ("5".equals(type)) {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan5));
        } else {
            future = threadPoolTaskScheduler.schedule(new myRunable(), new CronTrigger(scheduledPlan1));
        }
        //启动定时器
        //StartTest();
        System.out.println("CHANGE TASK SUCCESS");
        return "CHANGE TASK SUCCESS";
    }

    /**
     * 定义定时任务执行的方法
     */
    public class myRunable implements Runnable {
        @Override
        public void run() {
            System.out.println("DYNAMIC TIMED TASK: " + new Date());
        }

    }

}
