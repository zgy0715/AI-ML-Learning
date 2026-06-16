package chapter1;

/**
 * 第1章：Java基础 - 流程控制
 * 期末考试重点：if-else、switch、循环、break/continue
 */
public class FlowControlDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. if-else语句 ===");
        int score = 85;
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 80) {
            System.out.println("良好");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }

        System.out.println("\n=== 2. switch语句 ===");
        // Java 7后支持String
        String day = "Monday";
        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                System.out.println(day + "是工作日");
                break;
            case "Saturday":
            case "Sunday":
                System.out.println(day + "是周末");
                break;
            default:
                System.out.println("无效的日期");
        }

        System.out.println("\n=== 3. for循环 ===");
        // 计算1到100的和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("1-100的和: " + sum);

        System.out.println("\n=== 4. while循环 ===");
        // 找出第一个能被3和5同时整除的数
        int n = 1;
        while (true) {
            if (n % 3 == 0 && n % 5 == 0) {
                System.out.println("第一个能被3和5同时整除的数: " + n);
                break;
            }
            n++;
        }

        System.out.println("\n=== 5. do-while循环 ===");
        int count = 0;
        do {
            System.out.print(count + " ");
            count++;
        } while (count < 5);
        System.out.println();

        System.out.println("\n=== 6. 嵌套循环与break/continue ===");
        // 打印九九乘法表（部分）
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d*%d=%-4d", j, i, i*j);
            }
            System.out.println();
        }

        // break跳出当前循环，continue跳过本次循环
        System.out.println("\n使用continue跳过偶数:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;  // 跳过偶数
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
