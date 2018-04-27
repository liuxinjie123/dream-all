package com.dream.controller.async;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dream.api.asy.AsyncTaskService;
import com.dream.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncTaskController {
    @Reference(version = "1.0.0")
    private AsyncTaskService asyncTaskService;

    @GetMapping("/test/1/{num}")
    public Result test001(@PathVariable int num) {
        for (int i = 0; i < num; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
        return Result.success();
    }

//    @GetMapping("/test/2/{num}")
//    public Result test002(@PathVariable int num) throws InterruptedException {
//        for (int i = 0; i < num; i++) {
//            System.out.println(" 002 " + asyncDubboService.asyncInvokeReturnFuture(i));
//        }
//        return Result.success();
//    }

}
