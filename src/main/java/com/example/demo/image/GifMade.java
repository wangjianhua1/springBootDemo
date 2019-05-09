package com.example.demo.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GifMade {
    public static void main(String[] args) throws InterruptedException {
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(""))));
//        ConcurrentHashMap<Integer,Integer> map=new ConcurrentHashMap<>();
        final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String,Integer>();
        map.put("key", 1);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    int key = map.get("key") + 1; //step 1
                    map.put("key", key);//step 2
                }
            });
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
//        executorService.shutdown();
    }
}
