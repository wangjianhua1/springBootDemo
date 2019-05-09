package com.example.demo.ratelimit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶，主要限制流入
 * 限流，多的请求直接拒绝
 * 针对方法级别配置
 */
public class TrafficShapper {
    private static final ConcurrentMap<String, RateLimiter> resourceLimitMap = Maps.newConcurrentMap();
    /**
     * 修改配置限流参数，方法级别
     *
     * @param resource
     * @param qps
     */
    public static void updateResourceQps(String resource, int qps) {
        RateLimiter limiter = resourceLimitMap.get(resource);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            RateLimiter putByOtherThread = resourceLimitMap.putIfAbsent(resource, limiter);
            if (putByOtherThread != null) {
                limiter = putByOtherThread;
            }
        }
        try {
            limiter.setRate(qps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回配置中稳定的速率，该速率单位为每秒多少许可数
     * 当RateLimiter为空时，标识未配置，则返回0
     *
     * @param resource
     * @return
     */
    public static double getRate(String resource) {
        RateLimiter limiter = resourceLimitMap.get(resource);
        if (limiter == null) {
            return 0d;
        }
        return limiter.getRate();
    }

    private RateLimiter rateLimiter = RateLimiter.create(10);

    public void doRequest(String threadName) {
        boolean isAcquired = rateLimiter.tryAcquire(1, 3000L, TimeUnit.MICROSECONDS);//rateLimiter.tryAcquire();
        if (isAcquired) {
            System.out.println(threadName + ":调用成功");
        } else {
            System.out.println(threadName + ":当前调用人数过多，请稍后重试");
        }
    }

    public static void main(String[] args) {
        final TrafficShapper shapper = new TrafficShapper();
        final CountDownLatch latch = new CountDownLatch(1);
        final Random random = new Random(10);
        for (int i = 0; i < 20; i++) {
            final int finalT = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        int sleepTime = random.nextInt(1000);
                        Thread.sleep(sleepTime);
                        shapper.doRequest("t-" + finalT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        latch.countDown();
        System.out.println("线程开始......");
    }

}
/**
 * maxThreads:Tomcat可创建的最大的线程数，每一个线程处理一个请求；
 * minSpareThreads:最小备用线程数，tomcat启动时的初始化的线程数；
 * maxSpareThreads:最大备用线程数，一旦创建的线程超过这个值，Tomcat就会关闭不再需要的socket线程；
 * acceptCount:指定当所有可以使用的处理请求的线程数都被使用时，可以放到处理队列中的请求数，就是被排队的请求数，超过这个数的请求将拒绝连接。
 * connnectionTimeout:网络连接超时，单位：毫秒。设置为0表示永不超时，这样设置有隐患的。通常可设置为30000毫秒。 enableLookups:是否允许DNS查询
 */
