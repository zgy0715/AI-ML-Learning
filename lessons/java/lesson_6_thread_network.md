# 第6课：多线程与网络编程

## 一、多线程基础

### 什么是多线程？

```
程序 → 进程 → 线程

单线程：一个任务做完再做下一个
多线程：多个任务同时进行

举例：
┌──────────────────────────────────────────────┐
│  单线程：你一个人既做饭又洗碗又扫地             │
│  多线程：你做饭的同时，洗衣机在洗衣服            │
│                                              │
│  CPU快速切换，让你感觉"同时"在做多件事           │
└──────────────────────────────────────────────┘
```

### 创建线程的两种方式

#### 方式1：继承Thread类

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        // 线程要执行的代码
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(100);  // 暂停100毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 使用
MyThread t1 = new MyThread();
MyThread t2 = new MyThread();
t1.start();   // ⚠️ 必须用start()，不是run()
t2.start();
```

#### 方式2：实现Runnable接口（推荐！）

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

// 使用
Thread t1 = new Thread(new MyRunnable(), "线程A");
Thread t2 = new Thread(new MyRunnable(), "线程B");
t1.start();
t2.start();

// 更简洁：用Lambda
Thread t3 = new Thread(() -> {
    System.out.println("Lambda线程");
});
t3.start();
```

> ⚠️ **start() vs run()：**
> - `start()`：创建新线程，执行run()中的代码
> - `run()`：在当前线程执行，不会创建新线程

### 线程的生命周期

```
新建 → 就绪 → 运行 → 死亡
         ↑       ↓
         └─ 阻塞 ┘

NEW：创建了Thread对象，还没start()
RUNNABLE：就绪状态，等待CPU调度
RUNNING：正在执行
BLOCKED/WAITING/TIMED_WAITING：阻塞（等待锁、sleep等）
TERMINATED：执行完毕
```

### 常用方法

```java
Thread t = new Thread(() -> {});

t.start();              // 启动线程
t.setName("我的线程");   // 设置线程名
t.getName();            // 获取线程名
t.setPriority(1);       // 设置优先级（1-10，默认5）
t.isAlive();            // 是否还活着
t.join();               // 等待该线程结束
t.interrupt();          // 中断线程
t.setDaemon(true);      // 设为守护线程（其他线程结束它也结束）

Thread.sleep(1000);     // 当前线程暂停1000毫秒（静态方法）
Thread.yield();         // 让出CPU（提示，不一定生效）
Thread.currentThread(); // 获取当前线程
```

---

## 二、线程同步（synchronized）

多个线程同时访问**共享资源**时，可能出问题。

```java
// 经典问题：两个线程同时操作一个变量
// 线程A读到count=0，线程B也读到count=0
// 两个都加1后写入，结果是1而不是2！

// 解决方案：synchronized（加锁）
public class Counter {
    private int count = 0;

    // 方式1：同步方法
    public synchronized void increment() {
        count++;
    }

    // 方式2：同步代码块
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
```

### 同步的三种方式

```java
// 1. 同步方法
public synchronized void method() {
    // 同一时刻只有一个线程能执行
}

// 2. 同步代码块
synchronized (lockObject) {
    // 临界区代码
}

// 3. 静态同步方法
public static synchronized void staticMethod() {
    // 锁的是类的Class对象
}
```

### 死锁

```java
// 死锁：两个线程互相等待对方释放锁
// 线程A持有锁1，等待锁2
// 线程B持有锁2，等待锁1
// 结果：永远等下去！

// 避免死锁：
// 1. 按固定顺序获取锁
// 2. 设置超时时间
// 3. 尽量减少锁的使用
```

---

## 三、线程池

频繁创建和销毁线程开销很大，用线程池可以**复用线程**。

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建固定大小的线程池
ExecutorService pool = Executors.newFixedThreadPool(3);

// 提交任务
for (int i = 0; i < 10; i++) {
    int num = i;
    pool.submit(() -> {
        System.out.println(Thread.currentThread().getName() + " 执行任务" + num);
    });
}

// 关闭线程池
pool.shutdown();
```

---

## 四、网络编程基础

### IP地址和端口

```
IP地址：计算机的唯一标识
├── IPv4：192.168.1.100
├── IPv6：2001:db8::1
└── 特殊IP：127.0.0.1（本机）

端口：应用程序的标识（0-65535）
├── HTTP：80
├── HTTPS：443
├── MySQL：3306
└── 常用：1024以上自定义

IP + 端口 = 套接字（Socket）地址
例：192.168.1.100:8080
```

### TCP编程（可靠，面向连接）

```java
import java.net.*;
import java.io.*;

// ===== 服务端 =====
ServerSocket server = new ServerSocket(8888);   // 监听端口
System.out.println("服务端启动，等待连接...");

Socket socket = server.accept();    // 阻塞，等待客户端连接
System.out.println("客户端已连接");

// 接收数据
BufferedReader br = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));
String msg = br.readLine();
System.out.println("收到: " + msg);

// 发送数据
PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
pw.println("你好，客户端！");

// 关闭
socket.close();
server.close();
```

```java
// ===== 客户端 =====
Socket socket = new Socket("localhost", 8888);  // 连接服务端

// 发送数据
PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
pw.println("你好，服务端！");

// 接收数据
BufferedReader br = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));
String reply = br.readLine();
System.out.println("服务端回复: " + reply);

socket.close();
```

### UDP编程（不可靠，无连接）

```java
import java.net.*;

// ===== 发送端 =====
DatagramSocket ds = new DatagramSocket();
String msg = "Hello UDP!";
byte[] data = msg.getBytes();
DatagramPacket dp = new DatagramPacket(
    data, data.length,
    InetAddress.getByName("localhost"), 8888
);
ds.send(dp);
ds.close();

// ===== 接收端 =====
DatagramSocket ds2 = new DatagramSocket(8888);
byte[] buf = new byte[1024];
DatagramPacket dp2 = new DatagramPacket(buf, buf.length);
ds2.receive(dp2);    // 阻塞，等待数据
String received = new String(dp2.getData(), 0, dp2.getLength());
System.out.println("收到: " + received);
ds2.close();
```

### TCP vs UDP

```
┌──────────────┬──────────────────┬──────────────────┐
│ 特性          │ TCP              │ UDP              │
├──────────────┼──────────────────┼──────────────────┤
│ 连接方式      │ 面向连接          │ 无连接            │
│ 可靠性        │ 可靠（确认重传）   │ 不可靠            │
│ 速度          │ 较慢              │ 较快              │
│ 用途          │ 文件传输、网页     │ 视频、游戏、DNS   │
│ Java类        │ Socket            │ DatagramSocket   │
└──────────────┴──────────────────┴──────────────────┘
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 线程 | thread | 程序执行的最小单位 |
| 进程 | process | 运行中的程序 |
| 同步 | synchronization | 多线程共享资源时的协调机制 |
| 死锁 | deadlock | 两个线程互相等待 |
| 线程池 | thread pool | 复用线程的容器 |
| 套接字 | socket | 网络通信的端点 |
| TCP | TCP | 可靠的传输协议 |
| UDP | UDP | 快速但不可靠的传输协议 |

---

> **下一课预告：** 第7课我们将学习Java反射机制——动态获取类信息、调用方法、访问字段。
