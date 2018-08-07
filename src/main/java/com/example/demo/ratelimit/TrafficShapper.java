package com.example.demo.ratelimit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentMap;

/**
 *
 * 限流
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
}
