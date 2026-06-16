package chapter4;

import java.util.*;

/**
 * 第4章：集合与泛型 - 泛型
 * 期末考试重点：泛型类、泛型方法、通配符、PECS原则
 */
public class GenericDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 泛型类 ===");
        // 使用泛型类
        Pair<Integer, String> pair1 = new Pair<>(1, "Hello");
        Pair<String, Double> pair2 = new Pair<>("Java", 3.14);
        System.out.println("pair1: " + pair1);
        System.out.println("pair2: " + pair2);

        System.out.println("\n=== 2. 泛型方法 ===");
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"A", "B", "C", "D"};
        Double[] doubleArray = {1.1, 2.2, 3.3};

        System.out.println("整数数组:");
        printArray(intArray);
        System.out.println("字符串数组:");
        printArray(strArray);
        System.out.println("浮点数组:");
        printArray(doubleArray);

        // 泛型方法：获取数组最大值
        System.out.println("整数最大值: " + getMax(intArray));
        System.out.println("字符串最大值: " + getMax(strArray));

        System.out.println("\n=== 3. 泛型接口 ===");
        // 实现泛型接口
        GenericInterface<String> stringImpl = new GenericImpl<>();
        stringImpl.setData("Hello Generics");
        System.out.println("泛型接口实现: " + stringImpl.getData());

        System.out.println("\n=== 4. 通配符 ===");
        // 无界通配符 <?>
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<String> strList = Arrays.asList("A", "B", "C");

        System.out.println("无界通配符:");
        printList(intList);
        printList(doubleList);
        printList(strList);

        // 上界通配符 <? extends Number>（读取）
        System.out.println("\n上界通配符（只读）:");
        System.out.println("整数列表总和: " + sumList(intList));
        System.out.println("浮点列表总和: " + sumList(doubleList));
        // printList(strList);  // 编译错误！String不是Number的子类

        // 下界通配符 <? super Integer>（写入）
        System.out.println("\n下界通配符（只写）:");
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("添加后: " + numberList);

        System.out.println("\n=== 5. PECS原则 ===");
        System.out.println("PECS: Producer Extends, Consumer Super");
        System.out.println("- 如果你需要从集合中读取数据（Producer），使用 extends");
        System.out.println("- 如果你需要向集合中写入数据（Consumer），使用 super");
        System.out.println("- 如果你既要读又要写，不要使用通配符");

        System.out.println("\n=== 6. 类型擦除 ===");
        System.out.println("Java泛型是编译时特性，运行时会被擦除");
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        System.out.println("stringList.class: " + stringList.getClass());
        System.out.println("integerList.class: " + integerList.getClass());
        System.out.println("两者运行时类型相同: " + (stringList.getClass() == integerList.getClass()));
    }

    // 泛型方法：打印数组
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // 泛型方法：获取最大值（需要T实现Comparable）
    public static <T extends Comparable<T>> T getMax(T[] array) {
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    // 无界通配符：打印任意类型列表
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 上界通配符：计算Number列表的总和
    public static double sumList(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    // 下界通配符：添加Integer到Number列表
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }
}

/**
 * 泛型类：键值对
 */
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

/**
 * 泛型接口
 */
interface GenericInterface<T> {
    void setData(T data);
    T getData();
}

/**
 * 泛型接口实现类
 */
class GenericImpl<T> implements GenericInterface<T> {
    private T data;

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
}
