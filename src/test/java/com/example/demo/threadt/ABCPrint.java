package com.example.demo.threadt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

//信号量，获取则减1，没有则等待，释放则加1
public class ABCPrint {
    public Semaphore seam_first_two = new Semaphore(0);
    public Semaphore seam_two_second = new Semaphore(0);

    public ABCPrint() {
    }

    public void first() throws InterruptedException {
        System.out.println("first");
        seam_first_two.release();
    }

    /**
     * 要想打印，必须先获取first执行完成的信号量
     * @throws InterruptedException
     */
    public void second() throws InterruptedException {
        seam_first_two.acquire();
        System.out.println("second");
        seam_two_second.release();
    }
    /**
     * 要想打印，必须先获取second执行完成的信号量
     * @throws InterruptedException
     */
    public void third() throws InterruptedException {
        seam_two_second.acquire();
        System.out.println("third");
    }


    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(1);
        ABCPrint foo = new ABCPrint();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        A.start();
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        B.start();
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        C.start();
        downLatch.countDown();
    }
}

class ABCPrint2 {
    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);

    public ABCPrint2() {
    }

    public void first() throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        System.out.println("first");
        second.countDown();
    }

    /**
     * 等待直到first执行完成再执行
     * @throws InterruptedException
     */
    public void second() throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        second.await();
        System.out.println("second");
        third.countDown();
    }

    public void third() throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        third.await();
        System.out.println("third");
    }


    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(1);
        ABCPrint2 foo = new ABCPrint2();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        A.start();
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        B.start();
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downLatch.await();
                    foo.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        C.start();
        downLatch.countDown();
    }
}
