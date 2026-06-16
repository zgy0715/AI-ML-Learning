package chapter6;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 第6章：多线程 - 线程同步与互斥
 * 期末考试重点：synchronized、Lock、死锁、线程安全
 */
public class SynchronizedDemo {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        System.out.println("=== 1. 同步问题演示 ===");
        // 创建多个线程同时操作共享资源
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count++;  // 非原子操作，线程不安全
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        // 等待所有线程结束
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("期望值: 10000, 实际值: " + count);
        System.out.println("注意：实际值可能小于10000（线程不安全）\n");

        System.out.println("=== 2. synchronized同步方法 ===");
        Counter counter = new Counter();
        count = 0;
        Runnable syncTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(syncTask);
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("synchronized同步后: " + counter.getCount());

        System.out.println("\n=== 3. synchronized同步代码块 ===");
        synchronized (lock) {
            System.out.println("同步代码块: 同一时间只有一个线程能执行");
        }

        System.out.println("\n=== 4. Lock锁（更灵活）===");
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            System.out.println("Lock锁: 更灵活的锁机制");
        } finally {
            reentrantLock.unlock();  // 必须在finally中释放
        }

        System.out.println("\n=== 5. 死锁演示 ===");
        System.out.println("死锁：两个线程互相等待对方释放锁");
        System.out.println("避免死锁：按固定顺序获取锁、设置超时时间");
    }
}

/**
 * 线程安全的计数器
 */
class Counter {
    private int count = 0;

    // 同步方法
    public synchronized void increment() {
        count++;
    }

    // 同步代码块
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
