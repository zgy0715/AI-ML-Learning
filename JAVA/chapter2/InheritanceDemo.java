package chapter2;

/**
 * 第2章：面向对象 - 继承与多态
 * 期末考试重点：extends、super、方法重写、多态、instanceof
 */
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 继承 ===");
        Dog dog = new Dog("旺财", 3);
        Cat cat = new Cat("咪咪", 2);

        dog.eat();  // 继承自Animal的方法
        dog.bark(); // Dog特有的方法

        cat.eat();
        cat.purr();

        System.out.println("\n=== 2. 多态 ===");
        // 父类引用指向子类对象
        Animal animal1 = new Dog("小黑", 4);
        Animal animal2 = new Cat("小白", 1);

        // 运行时根据实际对象类型调用方法（动态绑定）
        animal1.eat();  // 调用Dog的eat方法
        animal2.eat();  // 调用Cat的eat方法

        // 多态数组
        System.out.println("\n多态数组:");
        Animal[] animals = {new Dog("A", 1), new Cat("B", 2), new Dog("C", 3)};
        for (Animal a : animals) {
            a.eat();  // 根据实际类型调用
        }

        System.out.println("\n=== 3. instanceof与类型转换 ===");
        // 向下转型前先检查类型
        if (animal1 instanceof Dog) {
            Dog d = (Dog) animal1;  // 向下转型
            d.bark();  // 可以调用Dog特有方法
        }

        if (animal2 instanceof Cat) {
            Cat c = (Cat) animal2;
            c.purr();
        }

        System.out.println("\n=== 4. super关键字 ===");
        GraduateStudent gs = new GraduateStudent("研究生", 25, "Java研究");
        gs.display();  // 调用父类方法
    }
}

/**
 * 父类：动物
 */
class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + "正在吃东西");
    }

    public void sleep() {
        System.out.println(name + "正在睡觉");
    }
}

/**
 * 子类：狗
 */
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);  // 调用父类构造方法
    }

    // 方法重写（Override）
    @Override
    public void eat() {
        System.out.println(name + "正在啃骨头");
    }

    // Dog特有方法
    public void bark() {
        System.out.println(name + "汪汪叫");
    }
}

/**
 * 子类：猫
 */
class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(name + "正在吃鱼");
    }

    // Cat特有方法
    public void purr() {
        System.out.println(name + "发出呼噜声");
    }
}

/**
 * 研究生类（演示super的使用）
 */
class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(String name, int age, String researchTopic) {
        super(name, age);  // 调用父类Student的构造方法
        this.researchTopic = researchTopic;
    }

    @Override
    public void display() {
        super.display();  // 调用父类的display方法
        System.out.println("研究方向: " + researchTopic);
    }
}
