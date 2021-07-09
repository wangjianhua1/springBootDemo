package com.example.demo.Thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    public static ExecutorService executors = Executors.newFixedThreadPool(3);

    private int n;
    private volatile static int i;
    Semaphore sem_a = new Semaphore(0);
    Semaphore sem_b = new Semaphore(0);
    Semaphore sem_c = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (i < n) {
            try {
                if (i > 0) {
                    sem_a.acquire();
                }
                printNumber.accept(0);
                ++i;
                if (i % 2 == 0) {
                    sem_b.release();
                } else {
                    sem_c.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (i < n) {
            try {
                sem_b.acquire();
                if (i != 0 && i % 2 == 0) {
                    printNumber.accept(i);
                }
                sem_a.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (i < n) {
            try {
                sem_c.acquire();
                if (i != 0 && i % 2 == 1) {
                    printNumber.accept(i);
                }
                sem_a.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
//        executors.execute(() -> {
//            try {
//                zeroEvenOdd.zero(value -> System.out.println(value));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executors.execute(() -> {
//            try {
//                zeroEvenOdd.even(value -> System.out.println(value));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executors.execute(() -> {
//            try {
//                zeroEvenOdd.odd(value -> System.out.println(value));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
//        System.out.println(canCross(stones));

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        while (true) {
            executors.execute(() -> {
                map.putIfAbsent("a", "1");
                map.putIfAbsent("c", "1");
                map.putIfAbsent("e", "1");
                map.putIfAbsent("g", "1");
            });

            executors.execute(() -> {
                map.putIfAbsent("a", "2");
                map.putIfAbsent("c", "2");
                map.putIfAbsent("e", "2");
                map.putIfAbsent("f", "2");
            });

            executors.execute(() -> {
                map.putIfAbsent("a", "3");
                map.putIfAbsent("c", "3");
                map.putIfAbsent("e", "3");
                map.putIfAbsent("d", "3");
            });
//            System.out.println(JSON.toJSONString(map));
        }

    }


    public static boolean canCross(int[] stones) {
        //>=3
        int point = 1;
        for (int p = 0; p < stones.length; p++) {
            int s1 = stones[p];
            int s2 = stones[p + 1];
            int s3 = stones[p + 2];
            int step = canStep(s1, s2, s3);
            if (step == -1) {
                //6 8 12
                s1 = stones[p - 1];
                s2 = stones[p - 1 + 2];
                s3 = stones[p - 1 + 3];
                if (canStep(s1, s2, s3) != 0) {
                    return false;
                }
            } else if (step == 1) {
                s1 = stones[p - 1];
                s2 = stones[p - 1 + 2];
                s3 = stones[p - 1 + 3];
                if (canStep(s1, s2, s3) != 0) {
                    return false;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private static int canStep(int pre, int cur, int next) {
        int k = cur - pre;
        int fix = next - cur - k;
        if (fix == 1 || fix == 0 || fix == -1) {
            return 0;
        }
        if (k < fix) {
            return -1;
        } else {
            return 1;
        }
    }
}
