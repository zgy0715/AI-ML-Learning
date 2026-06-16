package chapter1;

/**
 * 第1章：Java基础 - 方法
 * 期末考试重点：定义、调用、重载、值传递
 */
public class MethodDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 方法定义与调用 ===");
        // 调用无返回值方法
        printMessage("Hello Java!");

        // 调用有返回值方法
        int result = add(10, 20);
        System.out.println("10 + 20 = " + result);

        // 调用带返回值的方法
        double area = calculateArea(5.0);
        System.out.println("半径为5的圆面积: " + area);

        System.out.println("\n=== 2. 方法重载(Overload) ===");
        // 同名方法，参数列表不同
        System.out.println("int相加: " + add(10, 20));
        System.out.println("double相加: " + add(10.5, 20.5));
        System.out.println("三个int相加: " + add(10, 20, 30));

        System.out.println("\n=== 3. 值传递演示 ===");
        // 基本类型：传递值的副本
        int x = 10;
        System.out.println("调用前x: " + x);
        changeValue(x);
        System.out.println("调用后x: " + x);  // 仍然是10

        // 引用类型：传递引用地址的副本
        int[] arr = {1, 2, 3};
        System.out.println("调用前arr[0]: " + arr[0]);
        changeArray(arr);
        System.out.println("调用后arr[0]: " + arr[0]);  // 变成了100

        System.out.println("\n=== 4. 可变参数 ===");
        System.out.println("求和(1,2,3): " + sum(1, 2, 3));
        System.out.println("求和(1,2,3,4,5): " + sum(1, 2, 3, 4, 5));

        System.out.println("\n=== 5. 递归 ===");
        System.out.println("5的阶乘: " + factorial(5));
        System.out.println("斐波那契数列第10项: " + fibonacci(10));
    }

    // 无返回值方法
    public static void printMessage(String msg) {
        System.out.println("消息: " + msg);
    }

    // 有返回值方法
    public static int add(int a, int b) {
        return a + b;
    }

    // 方法重载：参数类型不同
    public static double add(double a, double b) {
        return a + b;
    }

    // 方法重载：参数个数不同
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // 计算圆面积
    public static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }

    // 基本类型传递
    public static void changeValue(int num) {
        num = 100;  // 修改的是副本，不影响原值
        System.out.println("方法内num: " + num);
    }

    // 引用类型传递
    public static void changeArray(int[] array) {
        array[0] = 100;  // 通过引用修改数组内容
        System.out.println("方法内array[0]: " + array[0]);
    }

    // 可变参数
    public static int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    // 递归：阶乘
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // 递归：斐波那契数列
    public static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
