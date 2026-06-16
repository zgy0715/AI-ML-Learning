package chapter2;

/**
 * 第2章：面向对象 - 类与对象
 * 期末考试重点：类定义、构造方法、this关键字、封装
 */
public class ClassAndObjectDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 类的定义与对象创建 ===");
        // 使用构造方法创建对象
        Student stu1 = new Student("张三", 20);
        Student stu2 = new Student("李四", 21, "计算机科学");
        stu1.display();
        stu2.display();

        System.out.println("\n=== 2. 封装与访问控制 ===");
        // 通过setter设置私有属性
        stu1.setAge(21);
        stu1.setScore(95.5);
        System.out.println("修改后的信息:");
        stu1.display();

        // 通过getter获取私有属性
        System.out.println("姓名: " + stu1.getName());
        System.out.println("年龄: " + stu1.getAge());

        System.out.println("\n=== 3. 对象数组 ===");
        Student[] students = {
            new Student("王五", 19),
            new Student("赵六", 22),
            new Student("钱七", 20)
        };

        System.out.println("所有学生信息:");
        for (Student s : students) {
            s.display();
        }
    }
}

/**
 * 学生类 - 演示封装
 */
class Student {
    // 私有属性（封装）
    private String name;
    private int age;
    private String major;
    private double score;

    // 无参构造方法
    public Student() {
        this.name = "未知";
        this.age = 0;
    }

    // 带参构造方法
    public Student(String name, int age) {
        this.name = name;  // this指向当前对象
        this.age = age;
        this.major = "未定";
        this.score = 0;
    }

    // 全参构造方法
    public Student(String name, int age, String major) {
        this(name, age);  // 调用另一个构造方法
        this.major = major;
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

    public double getScore() {
        return score;
    }

    // Setter方法（带验证）
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("年龄设置无效！");
        }
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setScore(double score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        } else {
            System.out.println("分数设置无效！");
        }
    }

    // 显示学生信息
    public void display() {
        System.out.printf("姓名: %s, 年龄: %d, 专业: %s, 分数: %.1f%n",
                name, age, major, score);
    }
}
