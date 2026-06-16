# 第4课：集合与泛型

## 一、集合框架概述

Java集合框架用来**存储和操作一组对象**。和数组不同，集合的大小可以动态变化。

```
集合框架的两大接口：
┌──────────────────────────────────────────────────────┐
│                    Collection                          │
│  ├── List（有序，可重复）                               │
│  │   ├── ArrayList   → 数组实现，查询快，增删慢         │
│  │   ├── LinkedList  → 链表实现，增删快，查询慢         │
│  │   └── Vector      → 线程安全的ArrayList（了解）     │
│  │                                                    │
│  ├── Set（无序，不可重复）                              │
│  │   ├── HashSet     → 哈希表实现（最常用）             │
│  │   ├── LinkedHashSet → 保持插入顺序                  │
│  │   └── TreeSet     → 自动排序                        │
│  │                                                    │
│  └── Queue（队列）                                     │
│      ├── PriorityQueue → 优先队列                      │
│      └── Deque         → 双端队列                      │
│                                                       │
│                    Map（键值对）                        │
│  ├── HashMap       → 哈希表实现（最常用）               │
│  ├── LinkedHashMap → 保持插入顺序                      │
│  ├── TreeMap       → 自动按key排序                     │
│  └── Hashtable     → 线程安全（了解）                   │
└──────────────────────────────────────────────────────┘
```

### 和Python对比

| Java | Python | 特点 |
|------|--------|------|
| ArrayList | list | 有序，可重复 |
| HashSet | set | 无序，不可重复 |
| HashMap | dict | 键值对 |
| Iterator | for-in | 遍历集合 |

---

## 二、List（有序，可重复）

### ArrayList（最常用！）

```java
import java.util.ArrayList;

// 创建（泛型指定类型）
ArrayList<String> list = new ArrayList<>();

// 添加元素
list.add("张三");       // 在末尾添加
list.add("李四");
list.add("王五");
list.add(1, "赵六");   // 在索引1处插入

// 获取元素
String first = list.get(0);    // "张三"
int size = list.size();        // 4（长度）

// 修改元素
list.set(0, "张三丰");    // 把索引0的元素改为"张三丰"

// 删除元素
list.remove("李四");       // 按内容删除
list.remove(0);           // 按索引删除

// 查找
boolean has = list.contains("王五");  // true
int index = list.indexOf("王五");     // 查找位置

// 判断为空
list.isEmpty();            // false
```

### 遍历List

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

// 方式1：普通for循环
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// 方式2：增强for循环（推荐）
for (String s : list) {
    System.out.println(s);
}

// 方式3：forEach方法（Java 8+）
list.forEach(s -> System.out.println(s));
```

### LinkedList

```java
import java.util.LinkedList;

LinkedList<String> linkedList = new LinkedList<>();
linkedList.addFirst("头");    // 在头部添加
linkedList.addLast("尾");     // 在尾部添加
linkedList.getFirst();        // 获取头部
linkedList.getLast();         // 获取尾部
linkedList.removeFirst();     // 删除头部
linkedList.removeLast();      // 删除尾部
```

### ArrayList vs LinkedList

```
┌──────────────┬──────────────────┬──────────────────┐
│ 特性          │ ArrayList        │ LinkedList       │
├──────────────┼──────────────────┼──────────────────┤
│ 底层结构      │ 数组              │ 双向链表          │
│ 随机访问      │ 快 O(1)          │ 慢 O(n)          │
│ 插入/删除     │ 慢 O(n)          │ 快 O(1)          │
│ 内存占用      │ 少               │ 多（多存指针）     │
│ 推荐使用      │ 查询多的场景       │ 增删多的场景       │
└──────────────┴──────────────────┴──────────────────┘

考试常考：ArrayList用得最多！
```

---

## 三、Set（无序，不可重复）

### HashSet

```java
import java.util.HashSet;

HashSet<String> set = new HashSet<>();
set.add("苹果");
set.add("香蕉");
set.add("苹果");     // 重复！不会添加
set.add("橙子");

System.out.println(set.size());          // 3（不是4）
System.out.println(set.contains("苹果")); // true

// 删除
set.remove("香蕉");

// 遍历（无序！每次遍历顺序可能不同）
for (String fruit : set) {
    System.out.println(fruit);
}
```

### 利用Set去重

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("A");   // 重复
list.add("C");
list.add("B");   // 重复

// 利用Set去重
HashSet<String> uniqueSet = new HashSet<>(list);
System.out.println(uniqueSet);  // [A, B, C]

// 转回List
ArrayList<String> uniqueList = new ArrayList<>(uniqueSet);
```

### TreeSet（自动排序）

```java
import java.util.TreeSet;

TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(30);
treeSet.add(10);
treeSet.add(20);
treeSet.add(5);

// 自动排序（升序）
for (int n : treeSet) {
    System.out.print(n + " ");   // 5 10 20 30
}
System.out.println();

// 获取最小和最大
treeSet.first();   // 5
treeSet.last();    // 30
```

---

## 四、Map（键值对）

### HashMap（最常用！）

```java
import java.util.HashMap;

// 创建（Key类型, Value类型）
HashMap<String, Integer> map = new HashMap<>();

// 添加键值对
map.put("张三", 95);
map.put("李四", 88);
map.put("王五", 72);
map.put("张三", 100);   // 重复key，会覆盖旧值

// 获取值
int score = map.get("张三");     // 100（被覆盖了）
int score2 = map.getOrDefault("赵六", 0);  // 不存在返回默认值0

// 判断
map.containsKey("张三");   // true
map.containsValue(88);     // true
map.size();                 // 3

// 删除
map.remove("王五");

// 遍历
// 方式1：遍历key
for (String key : map.keySet()) {
    System.out.println(key + " → " + map.get(key));
}

// 方式2：遍历entry（推荐）
for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// 方式3：forEach（Java 8+）
map.forEach((key, value) -> System.out.println(key + " → " + value));
```

### 利用Map统计词频

```java
String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};
HashMap<String, Integer> countMap = new HashMap<>();

for (String word : words) {
    countMap.put(word, countMap.getOrDefault(word, 0) + 1);
}
// 结果：{apple=3, banana=2, cherry=1}
```

---

## 五、Iterator（迭代器）

```java
import java.util.Iterator;
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

// 使用Iterator遍历
Iterator<String> it = list.iterator();
while (it.hasNext()) {      // 还有下一个？
    String s = it.next();   // 取出下一个
    System.out.println(s);
}

// ⚠️ 遍历时删除元素必须用Iterator
// for (String s : list) {
//     if (s.equals("B")) {
//         list.remove(s);   // ❌ 会抛出ConcurrentModificationException
//     }
// }

Iterator<String> it2 = list.iterator();
while (it2.hasNext()) {
    String s = it2.next();
    if (s.equals("B")) {
        it2.remove();       // ✅ 安全删除
    }
}
```

---

## 六、泛型（Generics）

泛型就是**在定义类/方法时指定类型参数**，使用时再确定具体类型。

### 为什么需要泛型？

```java
// 没有泛型：可以放任何类型，不安全
ArrayList list = new ArrayList();
list.add("Hello");
list.add(123);          // 编译不报错，运行时可能出错
String s = (String) list.get(1);  // ❌ 运行时ClassCastException

// 有泛型：只能放指定类型，编译时检查
ArrayList<String> safeList = new ArrayList<>();
safeList.add("Hello");
// safeList.add(123);   // ❌ 编译错误！类型不匹配
```

### 泛型类

```java
// 定义泛型类：用 <T> 作为类型占位符
public class Box<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }
}

// 使用
Box<String> strBox = new Box<>();
strBox.set("Hello");
String s = strBox.get();

Box<Integer> intBox = new Box<>();
intBox.set(100);
int n = intBox.get();
```

### 泛型方法

```java
// 泛型方法：在返回类型前加 <T>
public static <T> void printArray(T[] arr) {
    for (T item : arr) {
        System.out.print(item + " ");
    }
    System.out.println();
}

// 使用
Integer[] nums = {1, 2, 3, 4, 5};
String[] strs = {"A", "B", "C"};
printArray(nums);   // 1 2 3 4 5
printArray(strs);   // A B C
```

### 泛型通配符

```java
// ? extends Number：上界通配符，只能是Number及其子类
public static double sum(ArrayList<? extends Number> list) {
    double total = 0;
    for (Number n : list) {
        total += n.doubleValue();
    }
    return total;
}

// ? super Integer：下界通配符，只能是Integer及其父类
// ?：无界通配符，可以是任何类型
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 集合 | collection | 存储一组对象的容器 |
| 列表 | list | 有序、可重复的集合 |
| 集合（Set） | set | 无序、不可重复的集合 |
| 映射 | map | 键值对集合 |
| 迭代器 | iterator | 用于遍历集合 |
| 泛型 | generics | 类型参数化，编译时类型检查 |
| 哈希表 | hash table | 通过哈希函数快速查找的数据结构 |

---

> **下一课预告：** 第5课我们将学习Java I/O——文件读写、字节流、字符流、序列化。
