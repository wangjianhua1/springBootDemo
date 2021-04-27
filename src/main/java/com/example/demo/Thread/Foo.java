package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {

    public static ExecutorService executors = Executors.newFixedThreadPool(3);
    private volatile int num = 1;
    Lock lock;
    Condition condition1;
    Condition condition2;
    Condition condition3;

    public Foo() {
        lock = new ReentrantLock();
        condition1 = lock.newCondition();
        condition2 = lock.newCondition();
        condition3 = lock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                //针对第一个线程等待
                condition1.await();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            num = 2;
            //唤醒指定的等待线程
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                //针对第一个线程等待
                condition2.await();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printSecond.run();
            num = 3;
            //唤醒指定的等待线程
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                //针对第一个线程等待
                condition3.await();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printThird.run();
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Foo foo = new Foo();
        executors.execute(() -> {
            try {
                foo.second(() -> {
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executors.execute(() -> {
            try {
                foo.first(() -> {
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executors.execute(() -> {
            try {
                foo.third(() -> {
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executors.shutdown();
    }
}
