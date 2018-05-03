package com.dream.asyncService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Task {
    //定义一个随机对象.
    public static Random random =new Random();

    @Async //加入"异步调用"注解
    public void doTaskOne() throws InterruptedException {
        System.out.println("开始执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(30000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }


    @Async
    public void doTaskTwo() throws InterruptedException {
        System.out.println("开始执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(30000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }


    @Async
    public void doTaskThree() throws InterruptedException {
        System.out.println("开始执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(30000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
