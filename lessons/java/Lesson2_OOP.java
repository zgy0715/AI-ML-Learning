/**
 * 第2课：面向对象编程（OOP）
 * 包含：类与对象、构造方法、封装、继承、多态、抽象类、接口
 *
 * 编译运行：
 *   javac Lesson2_OOP.java
 *   java Lesson2_OOP
 */

// ============ Part 1: 类与对象 ============
class Student {
    // 属性（成员变量）
    String name;
    int age;
    double score;

    // 无参构造
    public Student() {
        this.name = "未知";
        this.age = 0;
        this.score = 0;
    }

    // 有参构造
    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // 方法
    public void showInfo() {
        System.out.println("姓名：" + name + "，年龄：" + age + "，成绩：" + score);
    }

    public boolean isPassed() {
        return score >= 60;
    }
}

// ============ Part 2: 封装 ============
class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // getter
    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    // 存款
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存入 " + amount + " 元，余额：" + balance);
        }
    }

    // 取款
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取出 " + amount + " 元，余额：" + balance);
        } else {
            System.out.println("余额不足！当前余额：" + balance);
        }
    }

    @Override
    public String toString() {
        return "账户[" + owner + "] 余额：" + balance;
    }
}

// ============ Part 3: 继承与多态 ============
class Animal {
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + "在吃东西");
    }

    public void makeSound() {
        System.out.println(name + "发出声音");
    }

    @Override
    public String toString() {
        return name + "(" + age + "岁)";
    }
}

class Dog extends Animal {
    String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类构造
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println(name + "：汪汪汪！");
    }

    public void fetch() {
        System.out.println(name + "去捡球了");
    }
}

class Cat extends Animal {
    boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    @Override
    public void makeSound() {
        System.out.println(name + "：喵喵喵！");
    }

    public void climb() {
        System.out.println(name + "爬上了树");
    }
}

// ============ Part 4: 抽象类 ============
abstract class Shape {
    String color;

    public Shape(String color) {
        this.color = color;
    }

    public void display() {
        System.out.println("这是一个" + color + "的图形");
    }

    // 抽象方法：没有方法体，子类必须实现
    public abstract double area();
    public abstract double perimeter();
}

class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "圆形[" + color + ", 半径=" + radius + "]";
    }
}

class Rectangle extends Shape {
    double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "矩形[" + color + ", " + width + "x" + height + "]";
    }
}

// ============ Part 5: 接口 ============
interface Flyable {
    int MAX_HEIGHT = 10000;  // 常量（默认 public static final）

    void fly();      // 抽象方法
    void land();     // 抽象方法
}

interface Swimmable {
    void swim();
}

// 一个类可以实现多个接口
class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void fly() {
        System.out.println(name + "拍着翅膀飞起来了");
    }

    @Override
    public void land() {
        System.out.println(name + "缓缓降落");
    }

    @Override
    public void swim() {
        System.out.println(name + "在水里游来游去");
    }

    @Override
    public void makeSound() {
        System.out.println(name + "：嘎嘎嘎！");
    }
}

// ============ 主类 ============
public class Lesson2_OOP {

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

    public static void main(String[] args) {
        System.out.println(repeat("=", 50));
        System.out.println("第2课：面向对象编程（OOP）");
        System.out.println(repeat("=", 50));

        // ========== Part 1: 类与对象 ==========
        printSection("1. 类与对象");

        // 无参构造
        Student s1 = new Student();
        s1.name = "张三";
        s1.age = 20;
        s1.score = 95.5;
        s1.showInfo();
        System.out.println("是否及格：" + s1.isPassed());

        // 有参构造
        Student s2 = new Student("李四", 21, 88.0);
        s2.showInfo();
        System.out.println("是否及格：" + s2.isPassed());

        // ========== Part 2: 封装 ==========
        printSection("2. 封装");

        BankAccount account = new BankAccount("张三", 1000);
        System.out.println(account);

        account.deposit(500);      // 存入500
        account.withdraw(200);     // 取出200
        account.withdraw(2000);    // 余额不足

        // account.balance = -999;  // ❌ 编译错误！private不能直接访问

        // ========== Part 3: 继承与多态 ==========
        printSection("3. 继承与多态");

        Dog dog = new Dog("旺财", 3, "金毛");
        Cat cat = new Cat("咪咪", 2, true);

        // 继承了父类的方法
        dog.eat();
        dog.makeSound();    // 子类重写的方法
        dog.fetch();        // 子类特有方法

        cat.eat();
        cat.makeSound();    // 子类重写的方法
        cat.climb();        // 子类特有方法

        // 多态：父类引用指向子类对象
        printSection("3.1 多态演示");
        Animal a1 = new Dog("小黑", 2, "拉布拉多");
        Animal a2 = new Cat("小白", 1, false);

        // 调用的是子类重写后的方法
        a1.makeSound();  // 小黑：汪汪汪！
        a2.makeSound();  // 小白：喵喵喵！

        // ⚠️ 多态下，父类引用不能调用子类特有方法
        // a1.fetch();   // ❌ 编译错误！Animal中没有fetch方法

        // 向下转型才能调用子类特有方法
        if (a1 instanceof Dog) {
            Dog d = (Dog) a1;
            d.fetch();     // ✅ 现在可以调用了
        }

        // ========== Part 4: 抽象类 ==========
        printSection("4. 抽象类");

        // Shape shape = new Shape("红");  // ❌ 抽象类不能创建对象

        Circle circle = new Circle("红色", 5);
        Rectangle rect = new Rectangle("蓝色", 4, 6);

        circle.display();
        System.out.println("面积：" + String.format("%.2f", circle.area()));
        System.out.println("周长：" + String.format("%.2f", circle.perimeter()));

        rect.display();
        System.out.println("面积：" + rect.area());
        System.out.println("周长：" + rect.perimeter());

        // 多态：用抽象类类型接收子类对象
        Shape s = new Circle("绿色", 3);
        System.out.println("\n多态调用 - " + s);
        System.out.println("面积：" + String.format("%.2f", s.area()));

        // ========== Part 5: 接口 ==========
        printSection("5. 接口");

        Duck duck = new Duck("唐老鸭", 5);

        // 可以当作Animal使用
        duck.eat();
        duck.makeSound();

        // 可以当作Flyable使用
        duck.fly();
        duck.land();

        // 可以当作Swimmable使用
        duck.swim();

        // 接口常量
        System.out.println("最大飞行高度：" + Flyable.MAX_HEIGHT + "米");

        // instanceof 判断
        System.out.println("\nduck是Animal吗？" + (duck instanceof Animal));
        System.out.println("duck是Flyable吗？" + (duck instanceof Flyable));
        System.out.println("duck是Swimmable吗？" + (duck instanceof Swimmable));

        // ========== 总结 ==========
        System.out.println("\n" + repeat("=", 50));
        System.out.println("第2课总结：");
        System.out.println("1. 类是模板，对象是实例，用new创建");
        System.out.println("2. 构造方法在new时自动调用，用于初始化");
        System.out.println("3. 封装：用private隐藏属性，用getter/setter访问");
        System.out.println("4. 继承：用extends，子类获得父类的属性和方法");
        System.out.println("5. 多态：父类引用指向子类对象，调用子类重写的方法");
        System.out.println("6. 抽象类：不能创建对象，子类必须实现抽象方法");
        System.out.println("7. 接口：用implements，一个类可以实现多个接口");
        System.out.println(repeat("=", 50));
    }
}
