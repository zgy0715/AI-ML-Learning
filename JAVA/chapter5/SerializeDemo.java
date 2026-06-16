package chapter5;

import java.io.*;

/**
 * 第5章：I/O流 - 对象序列化
 * 期末考试重点：Serializable接口、transient关键字、序列化版本号
 */
public class SerializeDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 对象序列化与反序列化 ===");

        // 创建对象
        Student student = new Student("张三", 20, "计算机科学", "password123");
        System.out.println("序列化前: " + student);

        // 序列化（写入文件）
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(student);
            System.out.println("对象序列化成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化（读取文件）
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student restored = (Student) ois.readObject();
            System.out.println("反序列化后: " + restored);
            System.out.println("password字段: " + restored.getPassword());  // 应该是null（transient）
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. 序列化版本号 ===");
        System.out.println("serialVersionUID用于版本控制，防止序列化和反序列化不兼容");
        System.out.println("建议显式定义serialVersionUID，而不是使用自动生成的");

        System.out.println("\n=== 3. 序列化注意事项 ===");
        System.out.println("1. 静态变量不会被序列化");
        System.out.println("2. transient修饰的字段不会被序列化");
        System.out.println("3. 序列化是递归的，对象中的其他对象也会被序列化");
        System.out.println("4. 实现Serializable接口的父类，子类自动可序列化");
    }
}

/**
 * 可序列化的学生类
 */
class Student implements Serializable {
    // 显式定义版本号（推荐）
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String major;

    // transient字段不会被序列化
    private transient String password;

    // 静态变量不会被序列化
    private static String schoolName = "Java大学";

    public Student(String name, int age, String major, String password) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.password = password;
    }

    // Getter方法
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", major='" + major + "'}";
    }
}
