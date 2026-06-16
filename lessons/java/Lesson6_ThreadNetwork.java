/**
 * 第6课：多线程与网络编程
 * 包含：Thread、Runnable、synchronized、线程池、Socket编程
 *
 * 编译运行：
 *   javac Lesson6_ThreadNetwork.java
 *   java Lesson6_ThreadNetwork
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lesson6_ThreadNetwork {

    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void printSection(String title) {
        System.out.println("\n" + title);
        System.out.println(repeat("-", 30));
    }

    // ========== Part 1: 继承Thread ==========
    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + ": 第" + (i + 1) + "次");
                try {
                    Thread.sleep(100);  // 暂停100毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ========== Part 2: 实现Runnable ==========
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": 第" + (i + 1) + "次");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ========== Part 3: 线程同步 ==========
    static class Counter {
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

    // ========== 主方法 ==========
    public static void main(String[] args) throws Exception {
        System.out.println(repeat("=", 50));
        System.out.println("第6课：多线程与网络编程");
        System.out.println(repeat("=", 50));

        // ========== Part 1: 继承Thread ==========
        printSection("1. 继承Thread创建线程");

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.setName("线程A");
        t2.setName("线程B");
        t1.start();
        t2.start();
        t1.join();  // 等待t1结束
        t2.join();  // 等待t2结束

        // ========== Part 2: 实现Runnable ==========
        printSection("2. 实现Runnable创建线程（推荐）");

        Thread t3 = new Thread(new MyRunnable(), "线程C");
        Thread t4 = new Thread(new MyRunnable(), "线程D");
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        // Lambda写法
        System.out.println("\nLambda线程：");
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Lambda: " + i);
            }
        }, "Lambda线程");
        t5.start();
        t5.join();

        // ========== Part 3: 线程同步 ==========
        printSection("3. 线程同步（synchronized）");

        Counter counter = new Counter();
        // 10个线程同时加1000次
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("10个线程各加1000次，期望10000，实际: " + counter.getCount());

        // ========== Part 4: 线程池 ==========
        printSection("4. 线程池");

        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 6; i++) {
            int num = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务" + num);
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);

        // ========== Part 5: TCP Socket ==========
        printSection("5. TCP Socket编程");

        // 在新线程中启动服务端
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(8888);
                System.out.println("服务端启动，监听端口8888...");
                Socket socket = server.accept();
                System.out.println("客户端已连接");

                // 接收
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                String msg = br.readLine();
                System.out.println("服务端收到: " + msg);

                // 发送
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("你好客户端，我是服务端！");

                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        Thread.sleep(500);  // 等服务端启动

        // 客户端
        Socket client = new Socket("localhost", 8888);
        PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
        pw.println("你好服务端，我是客户端！");

        BufferedReader br = new BufferedReader(
            new InputStreamReader(client.getInputStream()));
        String reply = br.readLine();
        System.out.println("客户端收到: " + reply);

        client.close();
        serverThread.join();

        // ========== Part 6: UDP ==========
        printSection("6. UDP编程");

        // 接收端
        Thread udpReceiver = new Thread(() -> {
            try {
                DatagramSocket ds = new DatagramSocket(9999);
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                String received = new String(dp.getData(), 0, dp.getLength(), "UTF-8");
                System.out.println("UDP收到: " + received);
                ds.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        udpReceiver.start();
        Thread.sleep(300);

        // 发送端
        DatagramSocket sender = new DatagramSocket();
        String msg = "Hello UDP!";
        DatagramPacket dp = new DatagramPacket(
            msg.getBytes("UTF-8"), msg.getBytes("UTF-8").length,
            InetAddress.getByName("localhost"), 9999
        );
        sender.send(dp);
        sender.close();
        udpReceiver.join();

        // ========== 总结 ==========
        System.out.println("\n" + repeat("=", 50));
        System.out.println("第6课总结：");
        System.out.println("1. 创建线程：继承Thread或实现Runnable（推荐）");
        System.out.println("2. start()启动线程，不要直接调用run()");
        System.out.println("3. synchronized解决线程安全问题");
        System.out.println("4. 线程池复用线程，避免频繁创建销毁");
        System.out.println("5. TCP可靠连接，UDP快速无连接");
        System.out.println(repeat("=", 50));
    }
}
