package exercises;

import java.util.*;

/**
 * 第4章练习题：集合与泛型
 * 包含：改错题、填空题、设计题
 */
public class Chapter4Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第4章练习题 ==========\n");

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

        // 错误1：类型不匹配
        System.out.println("错误1：类型不匹配");
        System.out.println("原代码：");
        System.out.println("  List list = new ArrayList<String>();");
        System.out.println("  list.add(123);  // 允许添加任何类型");
        System.out.println("分析：使用了原始类型，没有泛型约束");
        System.out.println("修正：List<Integer> list = new ArrayList<>();\n");

        // 错误2：并发修改异常
        System.out.println("错误2：并发修改异常");
        System.out.println("原代码：");
        System.out.println("  List<String> list = new ArrayList<>(Arrays.asList(\"A\", \"B\", \"C\"));");
        System.out.println("  for (String s : list) {");
        System.out.println("      if (s.equals(\"B\")) {");
        System.out.println("          list.remove(s);  // ConcurrentModificationException!");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println("分析：for-each循环中不能直接修改集合");
        System.out.println("修正：使用Iterator进行安全删除\n");

        // 错误3：HashMap的Key为null
        System.out.println("错误3：HashMap的Key为null");
        System.out.println("原代码：");
        System.out.println("  Map<String, Integer> map = new HashMap<>();");
        System.out.println("  map.put(null, 1);  // 允许，但可能有问题");
        System.out.println("分析：虽然HashMap允许null键，但实践中应避免");
        System.out.println("修正：使用非null键值\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("类型安全的List: " + list);

        List<String> list2 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Iterator<String> it = list2.iterator();
        while (it.hasNext()) {
            if (it.next().equals("B")) {
                it.remove();
            }
        }
        System.out.println("安全删除后: " + list2);
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：List添加元素
        System.out.println("填空1：List添加元素");
        System.out.println("代码：");
        System.out.println("  List<String> list = new ArrayList<>();");
        System.out.println("  list.______(\"Hello\");  // 第一空");
        System.out.println("答案：第一空填 add\n");

        // 填空2：Map获取值
        System.out.println("填空2：Map获取值");
        System.out.println("代码：");
        System.out.println("  Map<String, Integer> map = new HashMap<>();");
        System.out.println("  map.put(\"key\", 100);");
        System.out.println("  int value = map.______(\"key\");  // 第二空");
        System.out.println("答案：第二空填 get\n");

        // 填空3：Set判断是否存在
        System.out.println("填空3：Set判断是否存在");
        System.out.println("代码：");
        System.out.println("  Set<String> set = new HashSet<>();");
        System.out.println("  set.add(\"Java\");");
        System.out.println("  boolean exists = set.______(\"Java\");  // 第三空");
        System.out.println("答案：第三空填 contains\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        List<String> list = new ArrayList<>();
        list.add("Hello");
        System.out.println("List: " + list);

        Map<String, Integer> map = new HashMap<>();
        map.put("key", 100);
        int value = map.get("key");
        System.out.println("Map值: " + value);

        Set<String> set = new HashSet<>();
        set.add("Java");
        boolean exists = set.contains("Java");
        System.out.println("Set包含Java: " + exists);
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】使用HashMap和ArrayList设计一个简单的学生成绩管理系统");
        System.out.println("要求：");
        System.out.println("1. 存储学生姓名和成绩");
        System.out.println("2. 实现添加学生、查询成绩、打印所有学生成绩的功能");
        System.out.println("3. 实现按成绩排序的功能\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("class StudentManager {");
        System.out.println("    private Map<String, Integer> scores = new HashMap<>();");
        System.out.println("");
        System.out.println("    // 添加学生");
        System.out.println("    public void addStudent(String name, int score) {");
        System.out.println("        scores.put(name, score);");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    // 查询成绩");
        System.out.println("    public int getScore(String name) {");
        System.out.println("        return scores.getOrDefault(name, -1);");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    // 按成绩排序打印");
        System.out.println("    public void printByScore() {");
        System.out.println("        scores.entrySet().stream()");
        System.out.println("            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())");
        System.out.println("            .forEach(e -> System.out.println(e.getKey() + \": \" + e.getValue()));");
        System.out.println("    }");
        System.out.println("}\n");

        // 实际运行
        System.out.println("【运行结果】");
        StudentManager manager = new StudentManager();
        manager.addStudent("张三", 85);
        manager.addStudent("李四", 92);
        manager.addStudent("王五", 78);
        manager.addStudent("赵六", 95);

        System.out.println("李四的成绩: " + manager.getScore("李四"));
        System.out.println("\n按成绩排序:");
        manager.printByScore();
    }
}

/**
 * 学生管理器（用于练习3的实际运行）
 */
class StudentManager {
    private Map<String, Integer> scores = new HashMap<>();

    // 添加学生
    public void addStudent(String name, int score) {
        scores.put(name, score);
    }

    // 查询成绩
    public int getScore(String name) {
        return scores.getOrDefault(name, -1);
    }

    // 按成绩排序打印
    public void printByScore() {
        scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
