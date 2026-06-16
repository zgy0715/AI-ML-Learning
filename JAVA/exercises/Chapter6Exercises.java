package exercises;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第6章练习题：多线程
 * 包含：改错题、填空题、设计题
 */
public class Chapter6Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第6章练习题 ==========\n");

        // 练习1：代码改错
        System.out.println("--- 练习1：代码改错 ---");
        exercise1_codeError();

        // 练习2：填空题
        System.out.println("\n--- 练习2：填空题 ---");
        exercise2_filling();

        // 练习3：设计题
        System.out.println("\n--- 练习3：设计题 ---");
        exercise3_design();
    }

    /**
     * 练习1：代码改错
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：直接调用run方法
        System.out.println("错误1：直接调用run方法");
        System.out.println("原代码：");
        System.out.println("  MyThread t = new MyThread();");
        System.out.println("  t.run();  // 错误！这是普通方法调用");
        System.out.println("分析：应该调用start()方法来启动新线程");
        System.out.println("修正：t.start();\n");

        // 错误2：线程安全问题
        System.out.println("错误2：线程安全问题");
        System.out.println("原代码：");
        System.out.println("  private int count = 0;");
        System.out.println("  public void increment() { count++; }");
        System.out.println("分析：count++不是原子操作，多线程下不安全");
        System.out.println("修正：使用synchronized或AtomicInteger\n");

        // 错误3：未处理InterruptedException
        System.out.println("错误3：未处理InterruptedException");
        System.out.println("原代码：");
        System.out.println("  public void run() {");
        System.out.println("      Thread.sleep(1000);  // 编译错误！");
        System.out.println("  }");
        System.out.println("分析：sleep会抛出受检异常，必须捕获或声明");
        System.out.println("修正：try { Thread.sleep(1000); } catch (InterruptedException e) { ... }\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        AtomicInteger counter = new AtomicInteger(0);
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("原子类计数: " + counter.get());
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：启动线程
        System.out.println("填空1：启动线程");
        System.out.println("代码：");
        System.out.println("  Thread t = new Thread(() -> { ... });");
        System.out.println("  t.______();  // 第一空");
        System.out.println("答案：第一空填 start\n");

        // 填空2：同步方法
        System.out.println("填空2：同步方法");
        System.out.println("代码：");
        System.out.println("  public ______ void increment() {  // 第二空");
        System.out.println("      count++;");
        System.out.println("  }");
        System.out.println("答案：第二空填 synchronized\n");

        // 填空3：线程池提交任务
        System.out.println("填空3：线程池提交任务");
        System.out.println("代码：");
        System.out.println("  ExecutorService executor = Executors.newFixedThreadPool(3);");
        System.out.println("  executor.______(() -> { ... });  // 第三空");
        System.out.println("答案：第三空填 submit\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        AtomicInteger counter = new AtomicInteger(0);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementAndGet();
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("计数结果: " + counter.get());
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个生产者-消费者模型");
        System.out.println("要求：");
        System.out.println("1. 使用wait()和notify()进行线程通信");
        System.out.println("2. 确保线程安全");
        System.out.println("3. 生产者生产数据放入缓冲区，消费者取出数据\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("class Buffer {");
        System.out.println("    private List<Integer> list = new ArrayList<>();");
        System.out.println("    private int capacity;");
        System.out.println("");
        System.out.println("    public synchronized void produce(int item) throws InterruptedException {");
        System.out.println("        while (list.size() == capacity) {");
        System.out.println("            wait();  // 缓冲区满，等待");
        System.out.println("        }");
        System.out.println("        list.add(item);");
        System.out.println("        notifyAll();  // 通知消费者");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    public synchronized int consume() throws InterruptedException {");
        System.out.println("        while (list.isEmpty()) {");
        System.out.println("            wait();  // 缓冲区空，等待");
        System.out.println("        }");
        System.out.println("        int item = list.remove(0);");
        System.out.println("        notifyAll();  // 通知生产者");
        System.out.println("        return item;");
        System.out.println("    }");
        System.out.println("}\n");

        // 实际运行
        System.out.println("【运行结果】");
        Buffer buffer = new Buffer(5);

        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                    System.out.println("生产: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    int item = buffer.consume();
                    System.out.println("消费: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 缓冲区类（用于练习3）
 */
class Buffer {
    private java.util.List<Integer> list = new java.util.ArrayList<>();
    private int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (list.size() == capacity) {
            wait();  // 缓冲区满，等待
        }
        list.add(item);
        notifyAll();  // 通知消费者
    }

    public synchronized int consume() throws InterruptedException {
        while (list.isEmpty()) {
            wait();  // 缓冲区空，等待
        }
        int item = list.remove(0);
        notifyAll();  // 通知生产者
        return item;
    }
}
