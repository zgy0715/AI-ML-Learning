package chapter3;

/**
 * 第3章：常用Java API - String字符串
 * 期末考试重点：不可变性、常用方法、StringBuilder/StringBuffer
 */
public class StringDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. String的不可变性 ===");
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        System.out.println("s1 == s2: " + (s1 == s2));  // true（常量池）
        System.out.println("s1 == s3: " + (s1 == s3));  // false（不同对象）
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // true（值相等）

        System.out.println("\n=== 2. String常用方法 ===");
        String str = "Hello, Java World!";
        System.out.println("字符串: " + str);
        System.out.println("长度: " + str.length());
        System.out.println("获取指定位置字符: " + str.charAt(7));
        System.out.println("子串(7): " + str.substring(7));
        System.out.println("子串(0,5): " + str.substring(0, 5));
        System.out.println("查找'Java': " + str.indexOf("Java"));
        System.out.println("替换'Java'为'World': " + str.replace("Java", "World"));
        System.out.println("转大写: " + str.toUpperCase());
        System.out.println("转小写: " + str.toLowerCase());
        System.out.println("去除首尾空格: " + "  hello  ".trim());
        System.out.println("以'Hello'开头: " + str.startsWith("Hello"));
        System.out.println("以'!'结尾: " + str.endsWith("!"));
        System.out.println("包含'Java': " + str.contains("Java"));

        System.out.println("\n=== 3. 字符串分割与连接 ===");
        String csv = "apple,banana,cherry";
        String[] fruits = csv.split(",");
        System.out.println("分割后:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        String joined = String.join(" - ", fruits);
        System.out.println("连接后: " + joined);

        System.out.println("\n=== 4. 格式化 ===");
        String name = "张三";
        int age = 20;
        double score = 95.5;
        String formatted = String.format("姓名: %s, 年龄: %d, 分数: %.1f", name, age, score);
        System.out.println(formatted);

        System.out.println("\n=== 5. StringBuilder（可变字符串）===");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        System.out.println("拼接结果: " + sb.toString());
        System.out.println("插入: " + sb.insert(5, ","));
        System.out.println("删除: " + sb.delete(5, 6));
        System.out.println("反转: " + sb.reverse());
        System.out.println("替换: " + sb.replace(0, 5, "Hi"));

        System.out.println("\n=== 6. 字符串与基本类型转换 ===");
        // 字符串转基本类型
        int num = Integer.parseInt("123");
        double d = Double.parseDouble("3.14");
        boolean b = Boolean.parseBoolean("true");
        System.out.println("字符串转int: " + num);
        System.out.println("字符串转double: " + d);
        System.out.println("字符串转boolean: " + b);

        // 基本类型转字符串
        String numStr = String.valueOf(123);
        String dStr = Double.toString(3.14);
        String concat = "" + 123;  // 隐式转换
        System.out.println("int转字符串: " + numStr);
        System.out.println("double转字符串: " + dStr);
        System.out.println("拼接转字符串: " + concat);

        System.out.println("\n=== 7. StringBuffer（线程安全）===");
        StringBuffer buffer = new StringBuffer("Hello");
        buffer.append(" World");
        System.out.println("StringBuffer: " + buffer.toString());
    }
}
