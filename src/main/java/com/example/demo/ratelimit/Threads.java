package com.example.demo.ratelimit;

import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import java.util.concurrent.*;

public class Threads {
    public Executor executors = Executors.newScheduledThreadPool(3, new ScheduledExecutorFactoryBean());

    protected final static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3, // core thread pool size
            10, // maximum thread pool size
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20),
            new RejectedExecutionHandler() {
                /**
                 * 由调用者线程来执行
                 * @param r
                 * @param executor
                 */
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    if (!executor.isShutdown()) {
                        //log.debug("由调用者线程来执行!");
                        r.run();
                    }
                }
            });

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            final int final_i = i;
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("睡前t-" + final_i+"_"+executor.getActiveCount()+"_"+executor.getTaskCount());
                        Thread.sleep(500);
                        System.out.println("睡后t-" + final_i+"_"+executor.getActiveCount()+"_"+executor.getTaskCount());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executor.execute(thread);
        }
        System.out.println("线程启动完成");
    }

}
