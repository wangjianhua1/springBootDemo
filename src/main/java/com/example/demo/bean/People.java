package com.example.demo.bean;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.alibaba.fastjson.JSON;

import java.util.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by wangjianhua3 on 2018/5/31.
 */
public class People{
    private transient String name;
    private String id;

    public People(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
//        People people=new People("test","001");
//        System.out.println("==" + JSON.toJSONString(people));
//        LinkedList a=new LinkedList();
//        a.add(1);
//        a.add(2);
//        a.add(3);
//        Object remove = a.remove(0);
//
//        ArrayDeque arrayQueue=new ArrayDeque(3);
//        arrayQueue.add("a");
//        arrayQueue.add("b");
//        arrayQueue.pollFirst();

        ExecutorService service= ExecutorServiceUtil.newExecutorService();
        Future submit = service.submit(new A());
        System.out.println(JSON.toJSONString(submit));
        ScheduledExecutorService s= Executors.newScheduledThreadPool(10);
        ExecutorService sc= Executors.newCachedThreadPool();
        Future<?> submit1 = sc.submit(new A());
        System.out.println(JSON.toJSONString(submit));

    }
}

class A implements Runnable{
    @Override
    public void run() {
        System.out.println("123");
    }
}
