package chapter3;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 第3章：常用Java API - 包装类与工具类
 * 期末考试重点：自动装箱/拆箱、缓存机制、Math、Random、Date
 */
public class WrapperAndUtilDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 包装类与自动装箱/拆箱 ===");
        // 自动装箱：基本类型 -> 包装类
        Integer i1 = 100;  // 等价于 Integer i1 = Integer.valueOf(100);
        Double d1 = 3.14;
        Boolean b1 = true;

        // 自动拆箱：包装类 -> 基本类型
        int i2 = i1;  // 等价于 int i2 = i1.intValue();
        double d2 = d1;
        boolean b2 = b1;

        System.out.println("自动装箱: Integer i1 = " + i1);
        System.out.println("自动拆箱: int i2 = " + i2);

        System.out.println("\n=== 2. 包装类缓存机制 ===");
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        System.out.println("a == b: " + (a == b));  // true（缓存范围-128~127）
        System.out.println("c == d: " + (c == d));  // false（超出缓存范围）
        System.out.println("c.equals(d): " + c.equals(d));  // true（值相等）

        System.out.println("\n=== 3. 包装类常用方法 ===");
        // 进制转换
        int num = 255;
        System.out.println("二进制: " + Integer.toBinaryString(num));
        System.out.println("八进制: " + Integer.toOctalString(num));
        System.out.println("十六进制: " + Integer.toHexString(num));

        // 最大值最小值
        System.out.println("Integer最大值: " + Integer.MAX_VALUE);
        System.out.println("Integer最小值: " + Integer.MIN_VALUE);

        // 比较
        System.out.println("比较: " + Integer.compare(10, 20));

        System.out.println("\n=== 4. Math数学类 ===");
        System.out.println("π: " + Math.PI);
        System.out.println("e: " + Math.E);
        System.out.println("绝对值(-5): " + Math.abs(-5));
        System.out.println("最大值(10,20): " + Math.max(10, 20));
        System.out.println("最小值(10,20): " + Math.min(10, 20));
        System.out.println("2的10次方: " + Math.pow(2, 10));
        System.out.println("√16: " + Math.sqrt(16));
        System.out.println("ceil(3.2): " + Math.ceil(3.2));  // 向上取整
        System.out.println("floor(3.8): " + Math.floor(3.8));  // 向下取整
        System.out.println("round(3.5): " + Math.round(3.5));  // 四舍五入
        System.out.println("random[0,1): " + Math.random());

        System.out.println("\n=== 5. Random随机数类 ===");
        Random random = new Random();
        System.out.println("随机整数: " + random.nextInt());
        System.out.println("随机整数[0,100): " + random.nextInt(100));
        System.out.println("随机double: " + random.nextDouble());
        System.out.println("随机boolean: " + random.nextBoolean());

        System.out.println("\n=== 6. Date与Calendar日期类 ===");
        // Date
        Date now = new Date();
        System.out.println("当前时间: " + now);
        System.out.println("时间戳: " + now.getTime());

        // Calendar
        Calendar calendar = Calendar.getInstance();
        System.out.println("年: " + calendar.get(Calendar.YEAR));
        System.out.println("月: " + (calendar.get(Calendar.MONTH) + 1));  // 月份从0开始
        System.out.println("日: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + calendar.get(Calendar.MINUTE));
        System.out.println("秒: " + calendar.get(Calendar.SECOND));

        System.out.println("\n=== 7. BigDecimal精确计算 ===");
        java.math.BigDecimal bd1 = new java.math.BigDecimal("0.1");
        java.math.BigDecimal bd2 = new java.math.BigDecimal("0.2");
        java.math.BigDecimal sum = bd1.add(bd2);
        System.out.println("0.1 + 0.2 = " + sum);  // 精确结果

        double wrong = 0.1 + 0.2;
        System.out.println("double计算: 0.1 + 0.2 = " + wrong);  // 不精确
    }
}
