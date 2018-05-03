package com.dream.controller.async;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.api.asy.AsyncTaskService;
import com.dream.asyncService.AsyncTask;
import com.dream.asyncService.Task;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/async")
public class AsyncTaskController {
    @Reference(version = "1.0.0")
    private AsyncTaskService asyncTaskService;
    @Autowired
    private Task task;
    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/test/1/{num}")
    public Result test001(@PathVariable int num) {
        for (int i = 0; i < num; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
        return Result.success();
    }

    @GetMapping("/test/2")
    public Result test002() throws InterruptedException {
        System.out.println("开始执行Controller任务");
        long start = System.currentTimeMillis();
        // 异步任务
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        long end = System.currentTimeMillis();
        System.out.println("完成Controller任务，耗时：" + (end - start) + "毫秒");
        return Result.success();
    }

    @GetMapping("/test/3")
    public Result test003() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();
        Future<String> task3 = asyncTask.task3();
        String result = null;
        for (;;) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        result = "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
        return Result.success().setData(result);
    }

}
