package chapter4;

import java.util.*;

/**
 * 第4章：集合与泛型 - Map集合
 * 期末考试重点：HashMap、TreeMap、遍历方式、常用操作
 */
public class MapDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. Map基本操作 ===");
        // HashMap：基于哈希表，无序
        Map<String, Integer> scores = new HashMap<>();
        scores.put("张三", 85);
        scores.put("李四", 92);
        scores.put("王五", 78);
        scores.put("赵六", 95);
        System.out.println("HashMap: " + scores);

        // 获取值
        System.out.println("张三的成绩: " + scores.get("张三"));
        System.out.println("默认值: " + scores.getOrDefault("钱七", 0));

        // 判断是否存在
        System.out.println("包含'李四': " + scores.containsKey("李四"));
        System.out.println("包含值92: " + scores.containsValue(92));

        // 修改和删除
        scores.put("张三", 88);  // 修改
        scores.remove("王五");   // 删除
        System.out.println("修改后: " + scores);

        System.out.println("\n=== 2. Map遍历方式 ===");
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // 方式1：keySet()
        System.out.println("遍历方式1 - keySet:");
        for (String key : map.keySet()) {
            System.out.println("  " + key + " -> " + map.get(key));
        }

        // 方式2：values()
        System.out.println("遍历方式2 - values:");
        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        // 方式3：entrySet()（推荐，效率最高）
        System.out.println("遍历方式3 - entrySet:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // 方式4：forEach（Java 8+）
        System.out.println("遍历方式4 - forEach:");
        map.forEach((key, value) -> System.out.println("  " + key + " -> " + value));

        System.out.println("\n=== 3. TreeMap（排序）===");
        // TreeMap：基于红黑树，Key有序
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Banana", 3);
        treeMap.put("Apple", 5);
        treeMap.put("Orange", 2);
        treeMap.put("Grape", 4);
        System.out.println("TreeMap（按Key排序）: " + treeMap);
        System.out.println("第一个Key: " + treeMap.firstKey());
        System.out.println("最后一个Key: " + treeMap.lastKey());

        System.out.println("\n=== 4. LinkedHashMap（保持插入顺序）===");
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Java", 1);
        linkedHashMap.put("Python", 2);
        linkedHashMap.put("C++", 3);
        System.out.println("LinkedHashMap（保持插入顺序）: " + linkedHashMap);

        System.out.println("\n=== 5. Map常用操作 ===");
        // 统计单词出现次数
        String sentence = "hello world hello java world hello";
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : sentence.split(" ")) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("单词统计: " + wordCount);

        // 合并两个Map
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 3);
        map2.put("C", 4);

        // Java 8+ merge方法
        map2.forEach((key, value) -> map1.merge(key, value, Integer::sum));
        System.out.println("合并后: " + map1);

        System.out.println("\n=== 6. HashMap与HashTable区别 ===");
        System.out.println("HashMap: 线程不安全，允许null键值，效率高");
        System.out.println("HashTable: 线程安全，不允许null键值，效率低");
        System.out.println("推荐使用ConcurrentHashMap（分段锁，效率更高）");
    }
}
