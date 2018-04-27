package com.dream.api.asy;

import java.util.concurrent.Future;

public interface AsyncTaskService {

    void executeAsyncTask(int i);

//    Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException;
}
