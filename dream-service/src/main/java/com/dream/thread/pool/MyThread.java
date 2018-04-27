package com.dream.thread.pool;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(" threadName=" + Thread.currentThread().getName() + " is running.");
    }
}
