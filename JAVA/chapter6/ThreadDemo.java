package chapter6;

/**
 * 第6章：多线程 - 线程创建方式
 * 期末考试重点：Thread、Runnable、Callable、线程生命周期
 */
public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 继承Thread类 ===");
        MyThread thread1 = new MyThread("线程A");
        MyThread thread2 = new MyThread("线程B");
        thread1.start();  // 注意：是start()不是run()
        thread2.start();

        // 等待线程结束
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. 实现Runnable接口（推荐）===");
        MyRunnable runnable = new MyRunnable();
        Thread thread3 = new Thread(runnable, "线程C");
        Thread thread4 = new Thread(runnable, "线程D");
        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 3. 使用Lambda表达式（Java 8+）===");
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Lambda线程");
        thread5.start();

        try {
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 4. 线程常用方法 ===");
        System.out.println("currentThread(): 获取当前线程");
        System.out.println("getName(): 获取线程名称");
        System.out.println("setName(): 设置线程名称");
        System.out.println("start(): 启动线程");
        System.out.println("sleep(ms): 休眠指定毫秒");
        System.out.println("join(): 等待线程结束");
        System.out.println("yield(): 让出CPU时间片");
        System.out.println("setPriority(): 设置优先级(1-10)");
        System.out.println("setDaemon(true): 设置为守护线程");
    }
}

/**
 * 方式1：继承Thread类
 */
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName() + "运行: " + i);
            try {
                Thread.sleep(100);  // 休眠100ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 方式2：实现Runnable接口
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "运行: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
