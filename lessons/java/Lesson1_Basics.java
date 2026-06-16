/**
 * 第1课：Java基础入门
 * 包含：Hello World、变量、数据类型、运算符、控制流程、数组、方法
 *
 * 编译运行：
 *   javac Lesson1_Basics.java
 *   java Lesson1_Basics
 */

public class Lesson1_Basics {

    // Java 8兼容的repeat方法
    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    // ========== Part 1: Hello World ==========
    public static void helloWorld() {
        System.out.println(repeat("=", 50));
        System.out.println("第1课：Java基础入门");
        System.out.println(repeat("=", 50));

        System.out.println("\n1. Hello World");
        System.out.println(repeat("-", 30));
        // System.out.println 会自动换行
        // System.out.print   不会换行
        System.out.println("Hello, World!");
        System.out.println("你好，Java！");
        System.out.print("我不会换行...");
        System.out.println("我换行了");
    }

    // ========== Part 2: 变量与数据类型 ==========
    public static void variables() {
        System.out.println("\n2. 变量与数据类型");
        System.out.println(repeat("-", 30));

        // --- 2.1 基本数据类型 ---
        // 整数类型
        byte b = 127;           // byte: -128 ~ 127（1字节）
        short s = 32000;        // short: -32768 ~ 32767（2字节）
        int i = 100000;         // int: 约±21亿（4字节）—— 最常用
        long l = 10000000000L;  // long: 超大整数（8字节）—— 要加L

        // 浮点类型
        float f = 3.14F;        // float: 单精度（4字节）—— 要加F
        double d = 3.1415926;   // double: 双精度（8字节）—— 默认小数类型

        // 字符和布尔
        char c = 'A';           // char: 单个字符（单引号！）
        boolean flag = true;    // boolean: true或false

        // 字符串（不是基本类型，是引用类型）
        String name = "张三";   // String: 字符串（双引号！）

        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + flag);
        System.out.println("String: " + name);

        // --- 2.2 类型转换 ---
        System.out.println("\n类型转换演示：");

        // 自动转换（小→大，安全）
        int intVal = 100;
        double autoConvert = intVal;   // int → double，自动
        System.out.println("int " + intVal + " 自动转为 double: " + autoConvert);

        // 强制转换（大→小，可能丢失精度）
        double doubleVal = 3.99;
        int forcedConvert = (int) doubleVal;  // double → int，强制，截断！
        System.out.println("double " + doubleVal + " 强制转为 int: " + forcedConvert + "（小数被截断）");

        // 字符串和数字互转
        String numStr = "123";
        int parsed = Integer.parseInt(numStr);     // 字符串 → 整数
        String backToStr = String.valueOf(parsed); // 整数 → 字符串
        System.out.println("字符串 \"" + numStr + "\" → 整数: " + parsed);
        System.out.println("整数 " + parsed + " → 字符串: \"" + backToStr + "\"");

        // --- 2.3 变量命名规则 ---
        // 必须遵守：
        // 1. 以字母、下划线、美元符号开头
        // 2. 不能以数字开头
        // 3. 不能是关键字（int, class, public等）
        // 4. 区分大小写（myVar 和 myvar 是不同的）
        // 习惯用驼峰命名：firstName, getAge, calculateSum
        int myVariable = 1;      // ✅ 驼峰命名
        int _private = 2;        // ✅ 可以下划线开头
        int $dollar = 3;         // ✅ 可以美元符号开头（但不推荐）
        // int 2name = 4;        // ❌ 不能数字开头
        // int class = 5;        // ❌ 不能用关键字
    }

    // ========== Part 3: 运算符 ==========
    public static void operators() {
        System.out.println("\n3. 运算符");
        System.out.println(repeat("-", 30));

        // --- 3.1 算术运算符 ---
        int a = 10, b = 3;
        System.out.println(a + " + " + b + " = " + (a + b));   // 13
        System.out.println(a + " - " + b + " = " + (a - b));   // 7
        System.out.println(a + " * " + b + " = " + (a * b));   // 30
        System.out.println(a + " / " + b + " = " + (a / b));   // 3（整数除法！截断）
        System.out.println(a + " % " + b + " = " + (a % b));   // 1（取余）

        // ⚠️ 重要区别：Java整数除法截断，Python不截断
        System.out.println("\n⚠️ 整数除法区别：");
        System.out.println("Java: 10 / 3 = " + (10 / 3));           // 3
        System.out.println("Java: 10.0 / 3 = " + (10.0 / 3));       // 3.333...
        System.out.println("Java: (double)10 / 3 = " + ((double)10 / 3)); // 3.333...

        // --- 3.2 自增自减 ---
        int x = 5;
        int y = x++;    // 先用后加：y=5, x=6
        int z = ++x;    // 先加后用：x=7, z=7
        System.out.println("\n自增演示：");
        System.out.println("x++ 后: x=" + x + ", 之前赋值的y=" + y);
        System.out.println("++x 后: x=" + x + ", z=" + z);

        // --- 3.3 比较运算符 ---
        System.out.println("\n比较运算：");
        System.out.println("10 == 10: " + (10 == 10));    // true
        System.out.println("10 != 5:  " + (10 != 5));     // true
        System.out.println("10 > 5:   " + (10 > 5));      // true
        System.out.println("10 < 5:   " + (10 < 5));      // false

        // --- 3.4 逻辑运算符 ---
        System.out.println("\n逻辑运算：");
        System.out.println("true && true = " + (true && true));     // true  (与)
        System.out.println("true || false = " + (true || false));   // true  (或)
        System.out.println("!true = " + (!true));                   // false (非)

        // --- 3.5 字符串拼接 ---
        System.out.println("\n字符串拼接：");
        System.out.println("Java" + "是" + "最好的语言");  // Java是最好的语言
        System.out.println("1 + 1 = " + 1 + 1);           // 1 + 1 = 11（字符串拼接！）
        System.out.println("1 + 1 = " + (1 + 1));         // 1 + 1 = 2（加了括号先算数）
    }

    // ========== Part 4: 控制流程 ==========
    public static void controlFlow() {
        System.out.println("\n4. 控制流程");
        System.out.println(repeat("-", 30));

        // --- 4.1 if-else ---
        int score = 75;
        System.out.println("成绩: " + score);
        if (score >= 90) {
            System.out.println("等级: 优秀");
        } else if (score >= 80) {
            System.out.println("等级: 良好");
        } else if (score >= 60) {
            System.out.println("等级: 及格");
        } else {
            System.out.println("等级: 不及格");
        }

        // --- 4.2 switch ---
        int day = 3;
        System.out.println("\n星期: ");
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;      // ⚠️ 不加break会穿透！
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            default:
                System.out.println("周末");
                break;
        }

        // --- 4.3 for循环 ---
        System.out.println("\nfor循环（0到4）：");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // --- 4.4 增强for循环 ---
        int[] nums = {10, 20, 30, 40, 50};
        System.out.println("\n增强for循环遍历数组：");
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();

        // --- 4.5 while循环 ---
        System.out.println("\nwhile循环（倒计时）：");
        int count = 5;
        while (count > 0) {
            System.out.print(count + "...");
            count--;
        }
        System.out.println("发射！");

        // --- 4.6 do-while ---
        System.out.println("\ndo-while循环（至少执行一次）：");
        int num = 10;
        do {
            System.out.println("num = " + num);
            num--;
        } while (num > 10);    // 条件不满足，但已经执行了一次

        // --- 4.7 break和continue ---
        System.out.println("\nbreak演示（找到第一个偶数就停）：");
        int[] arr = {1, 3, 5, 8, 9, 10};
        for (int n : arr) {
            if (n % 2 == 0) {
                System.out.println("找到偶数: " + n);
                break;  // 跳出整个循环
            }
            System.out.println(n + " 是奇数，跳过");
        }

        System.out.println("\ncontinue演示（跳过偶数）：");
        for (int n : arr) {
            if (n % 2 == 0) {
                continue;   // 跳过本次，进入下一次
            }
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // ========== Part 5: 数组 ==========
    public static void arrays() {
        System.out.println("\n5. 数组");
        System.out.println(repeat("-", 30));

        // --- 5.1 一维数组 ---
        int[] arr1 = new int[5];            // 创建长度为5的数组，默认值0
        int[] arr2 = {1, 2, 3, 4, 5};      // 直接赋值
        int[] arr3 = new int[]{10, 20, 30}; // 另一种写法

        // 访问元素
        System.out.println("arr2[0] = " + arr2[0]);    // 1
        System.out.println("arr2.length = " + arr2.length); // 5

        // 修改元素
        arr2[2] = 99;
        System.out.println("修改后 arr2[2] = " + arr2[2]); // 99

        // 遍历数组
        System.out.print("遍历arr2: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        // 增强for循环遍历
        System.out.print("增强for遍历: ");
        for (int n : arr2) {
            System.out.print(n + " ");
        }
        System.out.println();

        // --- 5.2 常见数组操作 ---
        // 求最大值
        int max = arr2[0];
        for (int n : arr2) {
            if (n > max) {
                max = n;
            }
        }
        System.out.println("最大值: " + max);

        // 求和
        int sum = 0;
        for (int n : arr2) {
            sum += n;
        }
        System.out.println("总和: " + sum);

        // 求平均值
        double avg = (double) sum / arr2.length;
        System.out.println("平均值: " + avg);

        // --- 5.3 二维数组 ---
        System.out.println("\n二维数组：");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // ========== Part 6: 方法（函数） ==========
    // 方法需要定义在类里面，不能定义在其他方法里面

    // 无参数无返回值
    public static void sayHello() {
        System.out.println("Hello!");
    }

    // 有参数有返回值
    public static int add(int a, int b) {
        return a + b;
    }

    // 方法重载（Overload）—— 方法名相同，参数不同
    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // 判断偶数
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // 求阶乘（递归）
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void methods() {
        System.out.println("\n6. 方法（函数）");
        System.out.println(repeat("-", 30));

        // 调用方法
        sayHello();

        // 有参有返回值
        int result = add(3, 5);
        System.out.println("add(3, 5) = " + result);

        // 方法重载——根据参数自动匹配
        System.out.println("add(3, 5) = " + add(3, 5));         // 调用int版本
        System.out.println("add(3.5, 2.5) = " + add(3.5, 2.5)); // 调用double版本
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));   // 调用三参数版本

        // 判断偶数
        System.out.println("4是偶数吗？" + isEven(4));
        System.out.println("7是偶数吗？" + isEven(7));

        // 递归求阶乘
        System.out.println("5! = " + factorial(5));
        System.out.println("10! = " + factorial(10));
    }

    // ========== 主方法 ==========
    public static void main(String[] args) {
        helloWorld();
        variables();
        operators();
        controlFlow();
        arrays();
        methods();

        System.out.println("\n" + repeat("=", 50));
        System.out.println("第1课总结：");
        System.out.println("1. Java是强类型语言，变量必须声明类型");
        System.out.println("2. 8种基本数据类型要记住");
        System.out.println("3. 整数除法会截断（10/3=3，不是3.33）");
        System.out.println("4. 数组用 .length 获取长度");
        System.out.println("5. 方法重载：同名方法，参数不同");
        System.out.println(repeat("=", 50));
    }
}
