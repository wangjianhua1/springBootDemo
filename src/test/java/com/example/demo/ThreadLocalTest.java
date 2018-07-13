package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangjianhua3 on 2018/6/5.
 */
public class ThreadLocalTest {
     static ExecutorService executor= Executors.newSingleThreadExecutor();
//             Executors.newCachedThreadPool();

    public final static ThreadLocal<String> RESOURCE_1 = new ThreadLocal<String>();
    public final static ThreadLocal<String> RESOURCE_2 = new ThreadLocal<String>();


    static class A {
        public void setOne(String value) {
            ThreadLocalTest.RESOURCE_1.set(value);
        }
        public void setTwo(String value) {
            ThreadLocalTest.RESOURCE_2.set(value);
        }
    }

    static class B {
        public void display() {
            //打印当前线程名称
            System.out.println(ThreadLocalTest.RESOURCE_1.get() + ":" + ThreadLocalTest.RESOURCE_2.get());
        }
    }

    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        for (int i = 0; i < 10000; i++) {
            final String resouce1 = "线程-" + i;
            final String resouce2 = " value = (" + i + ")";
            final int k=i;
            //此可以证明是单个线程在运行，ThreadLocal里面存的值是一样的
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        if (k == 0){
//                            a.setOne(resouce1);
//                            a.setTwo(resouce2);
//                        }
//                        b.display();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
////                    finally {
////                        ThreadLocalTest.RESOURCE_1.remove();
////                        ThreadLocalTest.RESOURCE_2.remove();
////                    }
//                }
//            });
            //此处是重新开启线程在运行，ThreadLocal里面存的值是不一样的
            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            if (k == 0){
                                a.setOne(resouce1);
                                a.setTwo(resouce2);
                            }
                            b.display();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                    finally {
//                        ThreadLocalTest.RESOURCE_1.remove();
//                        ThreadLocalTest.RESOURCE_2.remove();
//                    }
                    }
                }
            ).start();
        }
    }

}
