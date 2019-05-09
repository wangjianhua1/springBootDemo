//package com.example.demo.zookeeper;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.leader.LeaderLatch;
//import org.apache.curator.framework.recipes.leader.LeaderSelector;
//import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
//import org.apache.curator.framework.state.ConnectionState;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///**
// * 实现多个应用实例只一个执行定时任务
// *
// * @author 594781919@qq.com
// */
//@Service
//public class TimerTaskService {
//
//    @Autowired
//    private CuratorFramework curatorFramework;
//
//    @Value("${server.port}")
//    private String port;
//
//    @Scheduled(cron = "0/10 * * * * *")
//    public void task() {
//        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, "/timerTask");
//        try {
//            leaderLatch.start();
//            // 尝试获得领导并超时
//            if (leaderLatch.await(2, TimeUnit.SECONDS)) {
//                System.out.println(new Date() + "    port=" + port + " 是领导");
//                // 定时任务的业务逻辑代码
//            } else {
//                System.out.println(new Date() + "    port=" + port + " 是从属");
//            }
//            // 这一步主要是为了让其他应用实例获取领导时超时，从而不让其他应用实例执行业务代码
//            Thread.sleep(2000);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                leaderLatch.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
