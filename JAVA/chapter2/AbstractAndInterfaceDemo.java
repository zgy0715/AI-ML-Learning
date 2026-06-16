package chapter2;

/**
 * 第2章：面向对象 - 抽象类与接口
 * 期末考试重点：abstract、interface、implements、区别
 */
public class AbstractAndInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 抽象类 ===");
        // 抽象类不能直接实例化
        // Shape shape = new Shape();  // 编译错误！

        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println("圆的面积: " + circle.getArea());
        System.out.println("矩形的面积: " + rectangle.getArea());

        System.out.println("\n=== 2. 接口 ===");
        // 接口不能直接实例化
        // Flyable flyable = new Flyable();  // 编译错误！

        Flyable bird = new Bird("麻雀");
        Flyable plane = new Airplane("波音747");

        bird.fly();
        plane.fly();

        System.out.println("\n=== 3. 一个类实现多个接口 ===");
        SuperMan superMan = new SuperMan();
        superMan.fly();    // 实现Flyable接口
        superMan.fight();  // 实现Fighter接口

        System.out.println("\n=== 4. 接口作为参数 ===");
        Animal dog = new Dog("旺财", 3);
        Animal cat = new Cat("咪咪", 2);
        makeAnimalEat(dog);
        makeAnimalEat(cat);
    }

    // 接口作为方法参数
    public static void makeAnimalEat(Animal animal) {
        animal.eat();
    }
}

/**
 * 抽象类：形状
 */
abstract class Shape {
    // 抽象方法（没有方法体，子类必须实现）
    public abstract double getArea();

    // 具体方法（有方法体）
    public void display() {
        System.out.println("这是一个形状，面积是: " + getArea());
    }
}

/**
 * 圆类（继承抽象类）
 */
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * 矩形类（继承抽象类）
 */
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

/**
 * 接口：可飞行的
 */
interface Flyable {
    // 接口中的方法默认是public abstract
    void fly();

    // 默认方法（Java 8+）
    default void land() {
        System.out.println("正在降落...");
    }
}

/**
 * 接口：可战斗的
 */
interface Fighter {
    void fight();
}

/**
 * 鸟类（实现接口）
 */
class Bird extends Animal implements Flyable {
    public Bird(String name) {
        super(name, 1);
    }

    @Override
    public void fly() {
        System.out.println(name + "正在飞翔");
    }

    @Override
    public void eat() {
        System.out.println(name + "正在吃虫子");
    }
}

/**
 * 飞机类（实现接口）
 */
class Airplane implements Flyable {
    private String model;

    public Airplane(String model) {
        this.model = model;
    }

    @Override
    public void fly() {
        System.out.println(model + "正在飞行");
    }
}

/**
 * 超人类（实现多个接口）
 */
class SuperMan implements Flyable, Fighter {
    @Override
    public void fly() {
        System.out.println("超人在飞翔");
    }

    @Override
    public void fight() {
        System.out.println("超人在战斗");
    }
}
