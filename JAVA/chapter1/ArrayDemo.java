package chapter1;

import java.util.Arrays;

/**
 * 第1章：Java基础 - 数组
 * 期末考试重点：声明、初始化、遍历、常用操作
 */
public class ArrayDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 数组声明与初始化 ===");
        // 方式1：先声明后初始化
        int[] arr1;
        arr1 = new int[5];  // 创建长度为5的数组，默认值为0

        // 方式2：声明时初始化
        int[] arr2 = {1, 2, 3, 4, 5};

        // 方式3：new关键字初始化
        int[] arr3 = new int[]{10, 20, 30};

        // 字符数组
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        System.out.println(new String(chars));  // 输出Hello

        System.out.println("\n=== 2. 数组属性与遍历 ===");
        // length属性获取数组长度
        System.out.println("arr2的长度: " + arr2.length);

        // 普通for循环遍历
        System.out.print("正向遍历: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        // 增强for循环遍历
        System.out.print("增强遍历: ");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("\n=== 3. 数组常用操作 ===");
        // 求最大值
        int[] scores = {85, 92, 78, 95, 88};
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        System.out.println("最大值: " + max);

        // 求和与平均值
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double avg = (double) sum / scores.length;
        System.out.println("平均值: " + avg);

        // 反转数组
        System.out.print("反转前: ");
        printArray(scores);
        for (int i = 0; i < scores.length / 2; i++) {
            int temp = scores[i];
            scores[i] = scores[scores.length - 1 - i];
            scores[scores.length - 1 - i] = temp;
        }
        System.out.print("反转后: ");
        printArray(scores);

        System.out.println("\n=== 4. 二维数组 ===");
        // 二维数组声明
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // 遍历二维数组
        System.out.println("3x3矩阵:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%-4d", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n=== 5. Arrays工具类 ===");
        int[] arr = {5, 2, 8, 1, 9, 3};
        System.out.print("排序前: ");
        printArray(arr);

        Arrays.sort(arr);  // 排序
        System.out.print("排序后: ");
        printArray(arr);

        int index = Arrays.binarySearch(arr, 8);  // 二分查找（需先排序）
        System.out.println("查找8的索引: " + index);

        int[] copy = Arrays.copyOf(arr, arr.length);  // 复制数组
        System.out.println("复制的数组: " + Arrays.toString(copy));
    }

    // 辅助方法：打印数组
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
