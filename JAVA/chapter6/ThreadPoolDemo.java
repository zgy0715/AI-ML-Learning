package chapter6;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第6章：多线程 - 线程池与高级特性
 * 期末考试重点：ExecutorService、Callable/Future、线程池参数
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 线程池基本使用 ===");
        // 创建固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 提交任务
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("任务" + taskId + "由线程" +
                        Thread.currentThread().getName() + "执行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. Callable与Future ===");
        ExecutorService executor2 = Executors.newFixedThreadPool(2);
        Callable<Integer> callableTask = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };

        Future<Integer> future = executor2.submit(callableTask);
        try {
            // 获取结果（会阻塞直到完成）
            Integer result = future.get();
            System.out.println("1-100的和: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor2.shutdown();

        System.out.println("\n=== 3. 线程池类型 ===");
        System.out.println("1. newFixedThreadPool: 固定大小线程池");
        System.out.println("2. newCachedThreadPool: 缓存线程池（按需创建）");
        System.out.println("3. newSingleThreadExecutor: 单线程池");
        System.out.println("4. newScheduledThreadPool: 定时线程池");

        System.out.println("\n=== 4. ThreadPoolExecutor参数 ===");
        System.out.println("corePoolSize: 核心线程数");
        System.out.println("maximumPoolSize: 最大线程数");
        System.out.println("keepAliveTime: 空闲线程存活时间");
        System.out.println("workQueue: 任务队列");
        System.out.println("threadFactory: 线程工厂");
        System.out.println("handler: 拒绝策略");

        System.out.println("\n=== 5. 原子类（线程安全）===");
        AtomicInteger atomicInt = new AtomicInteger(0);
        Runnable atomicTask = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicInt.incrementAndGet();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(atomicTask);
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("原子类计数: " + atomicInt.get());
    }
}
