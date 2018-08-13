package com.example.demo.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 利用信号量方式限流
 */
public class ExcutorSignal {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        //只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int taskNo = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        semp.acquire();//获取许可
                        String name = Thread.currentThread().getName();
                        System.out.println("开始" + name + "_" + taskNo);
                        Thread.sleep((long) (Math.random() * 10000));
                        semp.release();//访问完后释放
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
    }
}
