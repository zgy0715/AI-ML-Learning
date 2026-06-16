package exercises;

/**
 * 第1章练习题：Java基础
 * 包含：改错题、填空题、设计题
 */
public class Chapter1Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第1章练习题 ==========\n");

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
     * 找出并修复以下代码中的错误
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：数组越界
        System.out.println("错误1：数组越界");
        System.out.println("原代码：int[] arr = new int[5]; arr[5] = 10;");
        System.out.println("分析：数组索引从0开始，最大索引是4");
        System.out.println("修正：int[] arr = new int[5]; arr[4] = 10;\n");

        // 错误2：变量未初始化
        System.out.println("错误2：变量未初始化");
        System.out.println("原代码：int x; System.out.println(x);");
        System.out.println("分析：局部变量使用前必须初始化");
        System.out.println("修正：int x = 0; System.out.println(x);\n");

        // 错误3：除零错误
        System.out.println("错误3：除零错误");
        System.out.println("原代码：int a = 10, b = 0; int c = a / b;");
        System.out.println("分析：除数不能为0");
        System.out.println("修正：int a = 10, b = 2; int c = a / b;\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        int[] arr = new int[5];
        arr[4] = 10;
        System.out.println("arr[4] = " + arr[4]);

        int x = 0;
        System.out.println("x = " + x);

        int a = 10, b = 2;
        int c = a / b;
        System.out.println("a / b = " + c);
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码，使其正确运行：\n");

        // 填空1：计算两个整数的最大值
        System.out.println("填空1：计算两个整数的最大值");
        System.out.println("代码：");
        System.out.println("  public static int max(int a, int b) {");
        System.out.println("      if (a > b) {");
        System.out.println("          return ______;  // 第一空");
        System.out.println("      } else {");
        System.out.println("          return ______;  // 第二空");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println("答案：第一空填 a，第二空填 b\n");

        // 实际运行
        System.out.println("【运行结果】");
        System.out.println("max(10, 20) = " + max(10, 20));
        System.out.println("max(30, 15) = " + max(30, 15));

        // 填空2：打印数组元素
        System.out.println("\n填空2：打印数组元素");
        System.out.println("代码：");
        System.out.println("  int[] nums = {1, 2, 3, 4, 5};");
        System.out.println("  for (int i = 0; i < nums.______; i++) {  // 第三空");
        System.out.println("      System.out.print(nums[i] + \" \");");
        System.out.println("  }");
        System.out.println("答案：第三空填 length\n");

        // 实际运行
        System.out.println("【运行结果】");
        int[] nums = {1, 2, 3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个方法，接收一个整数数组，返回数组中的最大值、最小值和平均值");
        System.out.println("要求：使用一个方法完成，可以通过数组或对象返回多个值\n");

        System.out.println("【参考答案】");
        System.out.println("方法实现：");
        System.out.println("  public static double[] analyzeArray(int[] arr) {");
        System.out.println("      int max = arr[0];");
        System.out.println("      int min = arr[0];");
        System.out.println("      int sum = 0;");
        System.out.println("      for (int num : arr) {");
        System.out.println("          if (num > max) max = num;");
        System.out.println("          if (num < min) min = num;");
        System.out.println("          sum += num;");
        System.out.println("      }");
        System.out.println("      double avg = (double) sum / arr.length;");
        System.out.println("      return new double[]{max, min, avg};");
        System.out.println("  }\n");

        // 实际运行
        System.out.println("【运行结果】");
        int[] testArr = {85, 92, 78, 95, 88};
        double[] result = analyzeArray(testArr);
        System.out.println("数组: [85, 92, 78, 95, 88]");
        System.out.println("最大值: " + (int) result[0]);
        System.out.println("最小值: " + (int) result[1]);
        System.out.println("平均值: " + result[2]);
    }

    // 辅助方法
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static double[] analyzeArray(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        int sum = 0;
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
            sum += num;
        }
        double avg = (double) sum / arr.length;
        return new double[]{max, min, avg};
    }
}
