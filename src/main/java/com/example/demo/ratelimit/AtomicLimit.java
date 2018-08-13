package com.example.demo.ratelimit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子计数限流方式
 * 简单粗暴的限流方式，没有平滑处理，需要根据场景使用
 */
public class AtomicLimit {
    private AtomicInteger requestCount=new AtomicInteger(0);

    public void doRequest(String threadName){
        try {
            if (requestCount.incrementAndGet() > 10) {
                System.out.println(threadName + "请求过多请稍后重试");
            } else {
                System.out.println(threadName + "您的请求已经处理");
                try {
                    boolean b = Thread.holdsLock(this);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //mark 正常业务逻辑处理结束
            }
        }finally {
            requestCount.decrementAndGet();
        }
    }

    public static void main(String[] args) {
        final AtomicLimit simpleLimit=new AtomicLimit();
        final CountDownLatch latch=new CountDownLatch(1);//mark 保证线程同一时刻启动
        for (int i=0;i<50;i++){
            final int final_i=i;
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        simpleLimit.doRequest("t-" + final_i);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        latch.countDown();//当前计数为0则不执行操作，当计数从>0变成0后，所有线程同时启动
    }

}
