package exercises;

/**
 * 第3章练习题：常用Java API
 * 包含：改错题、填空题、设计题
 */
public class Chapter3Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第3章练习题 ==========\n");

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
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：字符串比较
        System.out.println("错误1：字符串比较");
        System.out.println("原代码：");
        System.out.println("  String s1 = \"hello\";");
        System.out.println("  String s2 = new String(\"hello\");");
        System.out.println("  if (s1 == s2) { ... }");
        System.out.println("分析：==比较的是引用地址，不是值内容");
        System.out.println("修正：if (s1.equals(s2)) { ... }\n");

        // 错误2：String不可变
        System.out.println("错误2：String不可变");
        System.out.println("原代码：");
        System.out.println("  String s = \"Hello\";");
        System.out.println("  s.concat(\" World\");");
        System.out.println("  System.out.println(s);  // 仍然是Hello");
        System.out.println("分析：String是不可变的，concat返回新字符串");
        System.out.println("修正：s = s.concat(\" World\"); 或 s += \" World\";\n");

        // 错误3：自动拆箱空指针
        System.out.println("错误3：自动拆箱空指针");
        System.out.println("原代码：");
        System.out.println("  Integer num = null;");
        System.out.println("  int value = num;  // NullPointerException!");
        System.out.println("分析：null不能自动拆箱");
        System.out.println("修正：if (num != null) { int value = num; }\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        String s1 = "hello";
        String s2 = new String("hello");
        System.out.println("s1.equals(s2): " + s1.equals(s2));

        String s = "Hello";
        s = s.concat(" World");
        System.out.println("修改后的字符串: " + s);

        Integer num = 100;
        if (num != null) {
            int value = num;
            System.out.println("安全拆箱: " + value);
        }
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：字符串转整数
        System.out.println("填空1：字符串转整数");
        System.out.println("代码：");
        System.out.println("  String numStr = \"123\";");
        System.out.println("  int num = ______.parseInt(numStr);  // 第一空");
        System.out.println("答案：第一空填 Integer\n");

        // 填空2：字符串分割
        System.out.println("填空2：字符串分割");
        System.out.println("代码：");
        System.out.println("  String csv = \"apple,banana,cherry\";");
        System.out.println("  String[] fruits = csv.______(\",\");  // 第二空");
        System.out.println("答案：第二空填 split\n");

        // 填空3：StringBuilder操作
        System.out.println("填空3：StringBuilder操作");
        System.out.println("代码：");
        System.out.println("  StringBuilder sb = new StringBuilder(\"abc\");");
        System.out.println("  String reversed = sb.______.toString();  // 第三空");
        System.out.println("答案：第三空填 reverse\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        String numStr = "123";
        int num = Integer.parseInt(numStr);
        System.out.println("字符串转整数: " + num);

        String csv = "apple,banana,cherry";
        String[] fruits = csv.split(",");
        System.out.println("分割结果: " + java.util.Arrays.toString(fruits));

        StringBuilder sb = new StringBuilder("abc");
        String reversed = sb.reverse().toString();
        System.out.println("反转结果: " + reversed);
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个工具类，提供以下方法：");
        System.out.println("1. 判断字符串是否为回文");
        System.out.println("2. 统计字符串中大写、小写字母的个数");
        System.out.println("3. 将字符串中每个单词的首字母大写\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("public class StringUtils {");
        System.out.println("    // 判断回文");
        System.out.println("    public static boolean isPalindrome(String s) {");
        System.out.println("        String reversed = new StringBuilder(s).reverse().toString();");
        System.out.println("        return s.equals(reversed);");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    // 统计大小写字母");
        System.out.println("    public static int[] countCase(String s) {");
        System.out.println("        int upper = 0, lower = 0;");
        System.out.println("        for (char c : s.toCharArray()) {");
        System.out.println("            if (Character.isUpperCase(c)) upper++;");
        System.out.println("            else if (Character.isLowerCase(c)) lower++;");
        System.out.println("        }");
        System.out.println("        return new int[]{upper, lower};");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("");

        // 实际运行
        System.out.println("【运行结果】");
        System.out.println("'aba'是回文: " + isPalindrome("aba"));
        System.out.println("'abc'是回文: " + isPalindrome("abc"));

        int[] counts = countCase("Hello World");
        System.out.println("'Hello World'大写字母数: " + counts[0]);
        System.out.println("'Hello World'小写字母数: " + counts[1]);
    }

    // 辅助方法
    public static boolean isPalindrome(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }

    public static int[] countCase(String s) {
        int upper = 0, lower = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) upper++;
            else if (Character.isLowerCase(c)) lower++;
        }
        return new int[]{upper, lower};
    }
}
