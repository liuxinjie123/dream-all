package com.dream.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutorSecond {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Thread[] array = new MyThread[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new MyThread();
        }
        for (int i = 0; i < array.length; i++) {
            Thread thread = array[i];
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    thread.start();
                }
            });
        }
        pool.shutdown();
    }
}
