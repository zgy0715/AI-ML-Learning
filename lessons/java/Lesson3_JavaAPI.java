/**
 * 第3课：Java常用API
 * 包含：String、StringBuilder、包装类、Math、异常处理
 *
 * 编译运行：
 *   javac Lesson3_JavaAPI.java
 *   java Lesson3_JavaAPI
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lesson3_JavaAPI {

    // Java 8兼容的repeat方法
    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void printSection(String title) {
        System.out.println("\n" + title);
        System.out.println(repeat("-", 30));
    }

    // ========== Part 1: String ==========
    public static void stringDemo() {
        printSection("1. String字符串");

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        // == 比较地址，equals比较内容
        System.out.println("s1 == s2: " + (s1 == s2));         // true（常量池同一个对象）
        System.out.println("s1 == s3: " + (s1 == s3));         // false（不同对象）
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true（内容相同）

        String s = "Hello, World!";
        System.out.println("\n原字符串: \"" + s + "\"");
        System.out.println("长度: " + s.length());
        System.out.println("charAt(0): " + s.charAt(0));
        System.out.println("substring(0, 5): " + s.substring(0, 5));
        System.out.println("substring(7): " + s.substring(7));
        System.out.println("indexOf(\"World\"): " + s.indexOf("World"));
        System.out.println("contains(\"World\"): " + s.contains("World"));
        System.out.println("toUpperCase: " + s.toUpperCase());
        System.out.println("toLowerCase: " + s.toLowerCase());
        System.out.println("replace: " + s.replace("World", "Java"));
        System.out.println("startsWith: " + s.startsWith("Hello"));
        System.out.println("endsWith: " + s.endsWith("!"));

        // 分割
        String csv = "张三,李四,王五,赵六";
        String[] names = csv.split(",");
        System.out.print("split结果: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // 去空格
        String padded = "  Hello  ";
        System.out.println("trim前: \"" + padded + "\"");
        System.out.println("trim后: \"" + padded.trim() + "\"");

        // ⚠️ String是不可变的
        String original = "Hello";
        String modified = original.concat(" World");  // 不改变原字符串
        System.out.println("\n原字符串: " + original);   // 还是Hello
        System.out.println("拼接后: " + modified);       // Hello World
    }

    // ========== Part 2: StringBuilder ==========
    public static void stringBuilderDemo() {
        printSection("2. StringBuilder（可变字符串）");

        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("初始: " + sb);

        sb.append(" World");       // 追加
        System.out.println("append后: " + sb);

        sb.append("!");            // 追加
        System.out.println("append后: " + sb);

        sb.insert(5, ",");         // 在位置5插入
        System.out.println("insert后: " + sb);

        sb.delete(5, 6);           // 删除位置5
        System.out.println("delete后: " + sb);

        sb.replace(6, 11, "Java"); // 替换位置6到11
        System.out.println("replace后: " + sb);

        sb.reverse();              // 反转
        System.out.println("reverse后: " + sb);

        // 转成String
        String result = sb.toString();
        System.out.println("toString: " + result);

        // 性能对比：拼接1000次
        System.out.println("\n性能对比（拼接1000次）：");

        // String拼接（慢）
        long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 1000; i++) {
            str += "a";
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("String拼接: " + time1 + "ms");

        // StringBuilder拼接（快）
        start = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb2.append("a");
        }
        String str2 = sb2.toString();
        long time2 = System.currentTimeMillis() - start;
        System.out.println("StringBuilder拼接: " + time2 + "ms");
    }

    // ========== Part 3: 包装类 ==========
    public static void wrapperDemo() {
        printSection("3. 包装类");

        // 自动装箱：基本类型 → 包装类
        Integer num1 = 100;       // 自动装箱
        Double num2 = 3.14;       // 自动装箱
        Boolean flag = true;      // 自动装箱

        System.out.println("Integer: " + num1);
        System.out.println("Double: " + num2);
        System.out.println("Boolean: " + flag);

        // 自动拆箱：包装类 → 基本类型
        int n1 = num1;            // 自动拆箱
        double n2 = num2;         // 自动拆箱
        System.out.println("拆箱后 int: " + n1);
        System.out.println("拆箱后 double: " + n2);

        // 字符串 ↔ 数字
        String str = "12345";
        int parsed = Integer.parseInt(str);
        double parsedD = Double.parseDouble("3.14");
        System.out.println("\n字符串 \"" + str + "\" → int: " + parsed);
        System.out.println("字符串 \"3.14\" → double: " + parsedD);

        String backStr = String.valueOf(parsed);
        String backStr2 = Integer.toString(parsed);
        System.out.println("int " + parsed + " → 字符串: \"" + backStr + "\"");
        System.out.println("Integer.toString: \"" + backStr2 + "\"");

        // 常用常量
        System.out.println("\nInteger.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Double.MAX_VALUE: " + Double.MAX_VALUE);

        // ⚠️ NumberFormatException
        try {
            int bad = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("\n数字格式异常: " + e.getMessage());
        }
    }

    // ========== Part 4: Math ==========
    public static void mathDemo() {
        printSection("4. Math数学工具");

        System.out.println("Math.abs(-10) = " + Math.abs(-10));
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
        System.out.println("Math.min(10, 20) = " + Math.min(10, 20));
        System.out.println("Math.pow(2, 10) = " + Math.pow(2, 10));
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.round(3.6) = " + Math.round(3.6));
        System.out.println("Math.ceil(3.2) = " + Math.ceil(3.2));
        System.out.println("Math.floor(3.8) = " + Math.floor(3.8));
        System.out.println("Math.PI = " + Math.PI);

        // 生成 [1, 100] 的随机数
        System.out.println("\n生成5个 [1, 100] 的随机数：");
        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 100) + 1;
            System.out.print(random + " ");
        }
        System.out.println();
    }

    // ========== Part 5: 异常处理 ==========
    // 声明异常
    public static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return a / b;
    }

    public static void exceptionDemo() {
        printSection("5. 异常处理");

        // --- 5.1 基本try-catch ---
        System.out.println("5.1 基本try-catch：");
        try {
            int result = 10 / 0;
            System.out.println("结果: " + result);  // 不会执行
        } catch (ArithmeticException e) {
            System.out.println("捕获算术异常: " + e.getMessage());
        } finally {
            System.out.println("finally执行了（无论如何都会执行）");
        }

        // --- 5.2 多重catch ---
        System.out.println("\n5.2 多重catch：");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[1]);     // 正常
            System.out.println(arr[5]);     // 越界！
            int r = 10 / 0;                 // 算术异常！
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("算术异常: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("其他异常: " + e.getMessage());
        }

        // --- 5.3 throws和throw ---
        System.out.println("\n5.3 throws和throw：");
        try {
            int result = divide(10, 0);
            System.out.println("结果: " + result);
        } catch (ArithmeticException e) {
            System.out.println("捕获异常: " + e.getMessage());
        }

        // --- 5.4 常见异常类型 ---
        System.out.println("\n5.4 常见异常类型演示：");

        // NullPointerException
        try {
            String s = null;
            s.length();
        } catch (NullPointerException e) {
            System.out.println("空指针异常: " + e.getClass().getSimpleName());
        }

        // ClassCastException
        try {
            Object obj = "Hello";
            Integer num = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("类型转换异常: " + e.getClass().getSimpleName());
        }

        // NumberFormatException
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("数字格式异常: " + e.getClass().getSimpleName());
        }

        // --- 5.5 自定义异常（了解） ---
        System.out.println("\n5.5 异常处理最佳实践：");
        System.out.println("1. 具体的异常类型放前面，通用的放后面");
        System.out.println("2. 不要捕获Exception或Throwable（太宽泛）");
        System.out.println("3. finally中通常关闭资源（文件、数据库连接等）");
        System.out.println("4. 不要用异常控制流程（性能差）");
    }

    // ========== 主方法 ==========
    public static void main(String[] args) {
        System.out.println(repeat("=", 50));
        System.out.println("第3课：Java常用API");
        System.out.println(repeat("=", 50));

        stringDemo();
        stringBuilderDemo();
        wrapperDemo();
        mathDemo();
        exceptionDemo();

        System.out.println("\n" + repeat("=", 50));
        System.out.println("第3课总结：");
        System.out.println("1. String用equals()比较内容，不用==");
        System.out.println("2. StringBuilder适合频繁拼接字符串");
        System.out.println("3. 包装类支持自动装箱和拆箱");
        System.out.println("4. Math类提供常用数学方法");
        System.out.println("5. try-catch-finally处理异常");
        System.out.println("6. throws声明异常，throw手动抛出异常");
        System.out.println(repeat("=", 50));
    }
}
