//package com.example.demo;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.RetryNTimes;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@EnableScheduling
////@ComponentScan(basePackages = {"com.example.demo.zookeeper.*"})
//@SpringBootApplication
//public class ZookeeperDemoApp {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ZookeeperDemoApp.class, args);
//    }
//
//    @Bean
//    public CuratorFramework curatorFramework(){
//        return CuratorFrameworkFactory.newClient("192.168.99.232:2181",new RetryNTimes(5,1000));
//    }
//}
