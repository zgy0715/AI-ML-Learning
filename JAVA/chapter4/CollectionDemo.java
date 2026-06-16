package chapter4;

import java.util.*;

/**
 * 第4章：集合与泛型 - Collection集合
 * 期末考试重点：List、Set、遍历方式、集合转换
 */
public class CollectionDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. List接口（有序可重复）===");
        // ArrayList：数组实现，查询快，增删慢
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Orange");
        arrayList.add("Apple");  // 允许重复
        System.out.println("ArrayList: " + arrayList);

        // LinkedList：链表实现，增删快，查询慢
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.addFirst("Cat");
        linkedList.addLast("Bird");
        System.out.println("LinkedList: " + linkedList);

        // List常用方法
        System.out.println("get(0): " + arrayList.get(0));
        System.out.println("indexOf('Banana'): " + arrayList.indexOf("Banana"));
        arrayList.set(1, "Grape");
        System.out.println("修改后: " + arrayList);
        arrayList.remove("Orange");
        System.out.println("删除后: " + arrayList);

        System.out.println("\n=== 2. Set接口（无序不重复）===");
        // HashSet：基于哈希表
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Java");
        hashSet.add("Python");
        hashSet.add("C++");
        hashSet.add("Java");  // 重复，不会添加
        System.out.println("HashSet: " + hashSet);

        // LinkedHashSet：保持插入顺序
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // TreeSet：排序（自然排序或自定义比较器）
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        System.out.println("TreeSet（排序后）: " + treeSet);

        System.out.println("\n=== 3. 集合遍历方式 ===");
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");

        // 方式1：普通for循环
        System.out.print("普通for: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        // 方式2：增强for循环
        System.out.print("增强for: ");
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();

        // 方式3：迭代器
        System.out.print("迭代器: ");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 方式4：Lambda表达式（Java 8+）
        System.out.print("Lambda: ");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println("\n=== 4. 集合与数组转换 ===");
        // 数组转集合
        String[] array = {"X", "Y", "Z"};
        List<String> arrayToList = Arrays.asList(array);
        System.out.println("数组转集合: " + arrayToList);

        // 集合转数组
        String[] listToArray = list.toArray(new String[0]);
        System.out.println("集合转数组: " + Arrays.toString(listToArray));

        System.out.println("\n=== 5. Collections工具类 ===");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("排序前: " + numbers);
        Collections.sort(numbers);
        System.out.println("排序后: " + numbers);
        System.out.println("最大值: " + Collections.max(numbers));
        System.out.println("最小值: " + Collections.min(numbers));
        System.out.println("反转: " + Collections.singletonList(1));

        // 线程安全的集合
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
    }
}
