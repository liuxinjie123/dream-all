package com.dream.impl.asy;

import com.alibaba.dubbo.config.annotation.Service;
import com.dream.api.asy.AsyncTaskService;

@Service(version = "1.0.0")
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Override
    public void executeAsyncTask(int i) {
        executeAsyncTaskMethod(i);
    }

    void executeAsyncTaskMethod(int i) {
        System.out.println("执行异步任务：" + i);
    }

//    /**
//     * 异常调用返回Future
//     *
//     * @param i
//     * @return
//     * @throws InterruptedException
//     */
////    @Override
//    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
//        return asyncInvokeReturnFutureMethod(i);
//    }
//
//    @Async
//    Future<String> asyncInvokeReturnFutureMethod(int i) throws InterruptedException {
//        Random random = new Random();// 默认构造方法
//        System.out.println("input is " + i);
//        Thread.sleep(1000 * random.nextInt(i));
//
//        Future<String> future = new AsyncResult<String>("success:" + i);// Future接收返回值，这里是String类型，可以指明其他类型
//
//        return future;
//    }
}
