/**
 * 第4课：集合与泛型
 * 包含：ArrayList、LinkedList、HashSet、TreeSet、HashMap、Iterator、泛型
 *
 * 编译运行：
 *   javac Lesson4_Collections.java
 *   java Lesson4_Collections
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

public class Lesson4_Collections {

    // Java 8兼容的repeat方法
    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void printSection(String title) {
        System.out.println("\n" + title);
        System.out.println(repeat("-", 30));
    }

    // ========== Part 1: ArrayList ==========
    public static void arraylistDemo() {
        printSection("1. ArrayList（动态数组）");

        // 创建（泛型指定类型）
        ArrayList<String> list = new ArrayList<>();

        // 添加元素
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add(1, "赵六");  // 在索引1处插入
        System.out.println("添加后: " + list);

        // 获取元素
        System.out.println("get(0): " + list.get(0));
        System.out.println("size(): " + list.size());

        // 修改元素
        list.set(0, "张三丰");
        System.out.println("set后: " + list);

        // 删除元素
        list.remove("李四");     // 按内容删
        System.out.println("删除李四后: " + list);
        list.remove(0);          // 按索引删
        System.out.println("删除索引0后: " + list);

        // 查找
        System.out.println("contains王五: " + list.contains("王五"));
        System.out.println("indexOf王五: " + list.indexOf("王五"));

        // 判断
        System.out.println("isEmpty: " + list.isEmpty());

        // --- 遍历 ---
        System.out.println("\n遍历方式：");

        // 方式1：普通for
        System.out.print("普通for: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        // 方式2：增强for（推荐）
        System.out.print("增强for: ");
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();

        // 方式3：forEach（Java 8+）
        System.out.print("forEach: ");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // --- 常用操作 ---
        // 排序
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(30);
        nums.add(10);
        nums.add(50);
        nums.add(20);
        System.out.println("\n排序前: " + nums);
        Collections.sort(nums);   // 升序排序
        System.out.println("排序后: " + nums);

        // 反转
        Collections.reverse(nums);
        System.out.println("反转后: " + nums);
    }

    // ========== Part 2: LinkedList ==========
    public static void linkedlistDemo() {
        printSection("2. LinkedList（链表）");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("B");
        linkedList.add("C");
        linkedList.addFirst("A");   // 头部添加
        linkedList.addLast("D");    // 尾部添加
        System.out.println("添加后: " + linkedList);

        System.out.println("getFirst: " + linkedList.getFirst());
        System.out.println("getLast: " + linkedList.getLast());

        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("删除首尾后: " + linkedList);
    }

    // ========== Part 3: HashSet ==========
    public static void hashsetDemo() {
        printSection("3. HashSet（无序，不可重复）");

        HashSet<String> set = new HashSet<>();
        set.add("苹果");
        set.add("香蕉");
        set.add("苹果");    // 重复！不会添加
        set.add("橙子");
        set.add("葡萄");
        System.out.println("添加后: " + set);
        System.out.println("size: " + set.size());  // 4，不是5

        System.out.println("contains苹果: " + set.contains("苹果"));

        set.remove("香蕉");
        System.out.println("删除香蕉后: " + set);

        // 遍历（无序）
        System.out.print("遍历: ");
        for (String fruit : set) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // --- 利用Set去重 ---
        System.out.println("\n利用Set去重：");
        ArrayList<String> listWithDups = new ArrayList<>();
        listWithDups.add("A");
        listWithDups.add("B");
        listWithDups.add("A");
        listWithDups.add("C");
        listWithDups.add("B");
        System.out.println("去重前: " + listWithDups);

        HashSet<String> uniqueSet = new HashSet<>(listWithDups);
        ArrayList<String> uniqueList = new ArrayList<>(uniqueSet);
        System.out.println("去重后: " + uniqueList);
    }

    // ========== Part 4: TreeSet ==========
    public static void treesetDemo() {
        printSection("4. TreeSet（自动排序）");

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(50);
        treeSet.add(20);
        treeSet.add(5);

        System.out.print("排序后: ");
        for (int n : treeSet) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.println("first(最小): " + treeSet.first());
        System.out.println("last(最大): " + treeSet.last());

        // 字符串TreeSet（按字母排序）
        TreeSet<String> strSet = new TreeSet<>();
        strSet.add("Banana");
        strSet.add("Apple");
        strSet.add("Cherry");
        System.out.println("字符串排序: " + strSet);
    }

    // ========== Part 5: HashMap ==========
    public static void hashmapDemo() {
        printSection("5. HashMap（键值对）");

        HashMap<String, Integer> map = new HashMap<>();

        // 添加
        map.put("张三", 95);
        map.put("李四", 88);
        map.put("王五", 72);
        map.put("张三", 100);  // 重复key，覆盖旧值
        System.out.println("添加后: " + map);

        // 获取
        System.out.println("get张三: " + map.get("张三"));
        System.out.println("getOrDefault赵六: " + map.getOrDefault("赵六", 0));

        // 判断
        System.out.println("containsKey张三: " + map.containsKey("张三"));
        System.out.println("containsValue88: " + map.containsValue(88));
        System.out.println("size: " + map.size());

        // 删除
        map.remove("王五");
        System.out.println("删除王五后: " + map);

        // --- 遍历 ---
        System.out.println("\n遍历方式：");

        // 方式1：keySet
        System.out.println("keySet遍历：");
        for (String key : map.keySet()) {
            System.out.println("  " + key + " → " + map.get(key));
        }

        // 方式2：entrySet（推荐）
        System.out.println("entrySet遍历：");
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // 方式3：forEach
        System.out.println("forEach遍历：");
        map.forEach((key, value) -> System.out.println("  " + key + " → " + value));

        // --- 应用：统计词频 ---
        System.out.println("\n统计词频：");
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("词频统计: " + countMap);
    }

    // ========== Part 6: Iterator ==========
    public static void iteratorDemo() {
        printSection("6. Iterator（迭代器）");

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        // 使用Iterator遍历
        System.out.println("Iterator遍历：");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 使用Iterator安全删除
        System.out.println("\n安全删除B和D：");
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            String s = it2.next();
            if (s.equals("B") || s.equals("D")) {
                it2.remove();   // ✅ 安全删除
            }
        }
        System.out.println("删除后: " + list);
    }

    // ========== Part 7: 泛型 ==========
    // 泛型类
    static class Box<T> {
        private T content;

        public void set(T content) {
            this.content = content;
        }

        public T get() {
            return content;
        }

        @Override
        public String toString() {
            return "Box[" + content + "]";
        }
    }

    // 泛型方法
    public static <T> void printArray(T[] arr) {
        for (T item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void genericDemo() {
        printSection("7. 泛型");

        // 泛型类
        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        System.out.println("String Box: " + strBox);
        System.out.println("get: " + strBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box: " + intBox);

        // 泛型方法
        System.out.println("\n泛型方法：");
        Integer[] nums = {1, 2, 3, 4, 5};
        String[] strs = {"A", "B", "C"};
        printArray(nums);
        printArray(strs);

        // 泛型集合
        System.out.println("\n泛型集合示例：");
        ArrayList<Box<String>> boxList = new ArrayList<>();
        Box<String> box1 = new Box<>();
        box1.set("第一个盒子");
        Box<String> box2 = new Box<>();
        box2.set("第二个盒子");
        boxList.add(box1);
        boxList.add(box2);

        for (Box<String> b : boxList) {
            System.out.println(b);
        }
    }

    // ========== 主方法 ==========
    public static void main(String[] args) {
        System.out.println(repeat("=", 50));
        System.out.println("第4课：集合与泛型");
        System.out.println(repeat("=", 50));

        arraylistDemo();
        linkedlistDemo();
        hashsetDemo();
        treesetDemo();
        hashmapDemo();
        iteratorDemo();
        genericDemo();

        System.out.println("\n" + repeat("=", 50));
        System.out.println("第4课总结：");
        System.out.println("1. ArrayList：有序，可重复，查询快，最常用");
        System.out.println("2. HashSet：无序，不可重复，用于去重");
        System.out.println("3. TreeSet：自动排序");
        System.out.println("4. HashMap：键值对，key不可重复");
        System.out.println("5. Iterator：遍历集合，安全删除元素");
        System.out.println("6. 泛型：编译时类型检查，更安全");
        System.out.println(repeat("=", 50));
    }
}
