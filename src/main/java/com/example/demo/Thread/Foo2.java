package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Foo2 {
    /**
     * CountDownLatch
     * synchronized
     */

    public static ExecutorService executors = Executors.newFixedThreadPool(3);

    Semaphore sem_1_2 = new Semaphore(0);
    Semaphore sem_2_3 = new Semaphore(0);

    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        sem_1_2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sem_1_2.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printSecond.run();
        sem_2_3.release();

    }

    public void third(Runnable printThird) throws InterruptedException {
        sem_2_3.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printThird.run();
        sem_2_3.release();
    }


    public static void main(String[] args) {
        Foo2 foo = new Foo2();
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
                foo.third(() -> {
                    System.out.println("third");
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
        executors.shutdown();
    }
}

