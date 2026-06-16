package chapter1;

/**
 * 第1章：Java基础 - 数据类型与变量
 * 期末考试重点：基本类型、类型转换、常量
 */
public class DataTypeDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 基本数据类型 ===");
        // 8种基本类型
        byte b = 127;          // -128 ~ 127
        short s = 32767;       // -32768 ~ 32767
        int i = 2147483647;    // 约21亿
        long l = 9876543210L;  // 注意L后缀
        float f = 3.14f;       // 注意f后缀
        double d = 3.1415926;
        char c = 'A';          // 单引号，字符
        boolean flag = true;   // true或false

        System.out.println("byte: " + b);
        System.out.println("int: " + i);
        System.out.println("double: " + d);
        System.out.println("char: " + c);

        System.out.println("\n=== 2. 类型转换 ===");
        // 自动转换（小转大）
        int num = 100;
        double autoConvert = num;  // int自动转double
        System.out.println("自动转换: int " + num + " -> double " + autoConvert);

        // 强制转换（大转小，可能丢失精度）
        double pi = 3.14159;
        int forcedConvert = (int) pi;  // 截断小数部分
        System.out.println("强制转换: double " + pi + " -> int " + forcedConvert);

        // 注意：boolean不能与其他类型转换
        // boolean b2 = (boolean) 1;  // 编译错误！

        System.out.println("\n=== 3. 常量 ===");
        final double PI = 3.14159265358979;
        final String GREETING = "Hello";
        System.out.println("常量PI: " + PI);
        System.out.println("常量GREETING: " + GREETING);
        // PI = 3.14;  // 编译错误！常量不能修改
    }
}
