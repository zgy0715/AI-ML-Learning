import chapter1.*;
import chapter2.*;
import chapter3.*;
import chapter4.*;
import chapter5.*;
import chapter6.*;
import chapter7.*;
import chapter8.*;
import chapter9.*;
import exercises.*;

import java.util.Scanner;

/**
 * Java期末考试复习系统
 * 主程序入口：选择要运行的章节
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║        Java大学期末考试复习系统                    ║");
        System.out.println("║        包含：基础、OOP、API、集合、I/O、          ║");
        System.out.println("║        多线程、网络、数据库、反射                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        while (running) {
            System.out.println("\n请选择要学习的章节：");
            System.out.println("1. 第1章：Java基础");
            System.out.println("2. 第2章：面向对象");
            System.out.println("3. 第3章：常用Java API");
            System.out.println("4. 第4章：集合与泛型");
            System.out.println("5. 第5章：I/O流");
            System.out.println("6. 第6章：多线程");
            System.out.println("7. 第7章：网络编程");
            System.out.println("8. 第8章：数据库编程");
            System.out.println("9. 第9章：反射机制");
            System.out.println("0. 退出");
            System.out.print("请输入选项: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runChapter1();
                    break;
                case 2:
                    runChapter2();
                    break;
                case 3:
                    runChapter3();
                    break;
                case 4:
                    runChapter4();
                    break;
                case 5:
                    runChapter5();
                    break;
                case 6:
                    runChapter6();
                    break;
                case 7:
                    runChapter7();
                    break;
                case 8:
                    runChapter8();
                    break;
                case 9:
                    runChapter9();
                    break;
                case 0:
                    running = false;
                    System.out.println("感谢使用，祝你考试顺利！");
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
            }
        }

        scanner.close();
    }

    /**
     * 第1章：Java基础
     */
    private static void runChapter1() {
        System.out.println("\n===== 第1章：Java基础 =====");
        System.out.println("1. 数据类型与变量");
        System.out.println("2. 流程控制");
        System.out.println("3. 数组");
        System.out.println("4. 方法");
        System.out.println("5. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                DataTypeDemo.main(null);
                break;
            case 2:
                FlowControlDemo.main(null);
                break;
            case 3:
                ArrayDemo.main(null);
                break;
            case 4:
                MethodDemo.main(null);
                break;
            case 5:
                Chapter1Exercises.main(null);
                break;
        }
    }

    /**
     * 第2章：面向对象
     */
    private static void runChapter2() {
        System.out.println("\n===== 第2章：面向对象 =====");
        System.out.println("1. 类与对象");
        System.out.println("2. 继承与多态");
        System.out.println("3. 抽象类与接口");
        System.out.println("4. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                ClassAndObjectDemo.main(null);
                break;
            case 2:
                InheritanceDemo.main(null);
                break;
            case 3:
                AbstractAndInterfaceDemo.main(null);
                break;
            case 4:
                Chapter2Exercises.main(null);
                break;
        }
    }

    /**
     * 第3章：常用Java API
     */
    private static void runChapter3() {
        System.out.println("\n===== 第3章：常用Java API =====");
        System.out.println("1. String字符串");
        System.out.println("2. 包装类与工具类");
        System.out.println("3. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                StringDemo.main(null);
                break;
            case 2:
                WrapperAndUtilDemo.main(null);
                break;
            case 3:
                Chapter3Exercises.main(null);
                break;
        }
    }

    /**
     * 第4章：集合与泛型
     */
    private static void runChapter4() {
        System.out.println("\n===== 第4章：集合与泛型 =====");
        System.out.println("1. Collection集合");
        System.out.println("2. Map集合");
        System.out.println("3. 泛型");
        System.out.println("4. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                CollectionDemo.main(null);
                break;
            case 2:
                MapDemo.main(null);
                break;
            case 3:
                GenericDemo.main(null);
                break;
            case 4:
                Chapter4Exercises.main(null);
                break;
        }
    }

    /**
     * 第5章：I/O流
     */
    private static void runChapter5() {
        System.out.println("\n===== 第5章：I/O流 =====");
        System.out.println("1. 字节流与字符流");
        System.out.println("2. 对象序列化");
        System.out.println("3. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                IODemo.main(null);
                break;
            case 2:
                SerializeDemo.main(null);
                break;
            case 3:
                Chapter5Exercises.main(null);
                break;
        }
    }

    /**
     * 第6章：多线程
     */
    private static void runChapter6() {
        System.out.println("\n===== 第6章：多线程 =====");
        System.out.println("1. 线程创建方式");
        System.out.println("2. 线程同步");
        System.out.println("3. 线程池");
        System.out.println("4. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                ThreadDemo.main(null);
                break;
            case 2:
                SynchronizedDemo.main(null);
                break;
            case 3:
                ThreadPoolDemo.main(null);
                break;
            case 4:
                Chapter6Exercises.main(null);
                break;
        }
    }

    /**
     * 第7章：网络编程
     */
    private static void runChapter7() {
        System.out.println("\n===== 第7章：网络编程 =====");
        System.out.println("1. TCP Socket编程");
        System.out.println("2. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                SocketDemo.main(null);
                break;
            case 2:
                Chapter7Exercises.main(null);
                break;
        }
    }

    /**
     * 第8章：数据库编程
     */
    private static void runChapter8() {
        System.out.println("\n===== 第8章：数据库编程 =====");
        System.out.println("1. JDBC基础");
        System.out.println("2. 连接池");
        System.out.println("3. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                JDBCDemo.main(null);
                break;
            case 2:
                ConnectionPoolDemo.main(null);
                break;
            case 3:
                Chapter8Exercises.main(null);
                break;
        }
    }

    /**
     * 第9章：反射机制
     */
    private static void runChapter9() {
        System.out.println("\n===== 第9章：反射机制 =====");
        System.out.println("1. 反射基础");
        System.out.println("2. 练习题");
        System.out.print("请选择: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1:
                    ReflectionDemo.main(null);
                    break;
                case 2:
                    Chapter9Exercises.main(null);
                    break;
            }
        } catch (Exception e) {
            System.out.println("运行出错: " + e.getMessage());
        }
    }
}
