package exercises;

/**
 * 第2章练习题：面向对象
 * 包含：改错题、填空题、设计题
 */
public class Chapter2Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第2章练习题 ==========\n");

        // 练习1：代码改错
        System.out.println("--- 练习1：代码改错 ---");
        exercise1_codeError();

        // 练习2：填空题
        System.out.println("\n--- 练习2：填空题 ---");
        exercise2_filling();

        // 练习3：设计题
        System.out.println("\n--- 练习3：设计题 ---");
        exercise3_design();
    }

    /**
     * 练习1：代码改错
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：抽象类实例化
        System.out.println("错误1：抽象类实例化");
        System.out.println("原代码：Shape shape = new Shape();");
        System.out.println("分析：抽象类不能直接实例化");
        System.out.println("修正：Shape shape = new Circle(5.0); (假设Circle是Shape的具体子类)\n");

        // 错误2：接口实现不完整
        System.out.println("错误2：接口实现不完整");
        System.out.println("原代码：");
        System.out.println("  interface Animal { void makeSound(); }");
        System.out.println("  class Cat implements Animal { }");
        System.out.println("分析：实现接口的类必须实现所有抽象方法");
        System.out.println("修正：");
        System.out.println("  class Cat implements Animal {");
        System.out.println("      public void makeSound() { System.out.println(\"Meow\"); }");
        System.out.println("  }\n");

        // 错误3：访问权限错误
        System.out.println("错误3：访问权限错误");
        System.out.println("原代码：");
        System.out.println("  class Person {");
        System.out.println("      private String name;");
        System.out.println("  }");
        System.out.println("  Person p = new Person();");
        System.out.println("  p.name = \"Tom\";  // 编译错误！");
        System.out.println("分析：private成员只能在类内部访问");
        System.out.println("修正：提供public的setter方法或改为public属性\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        Shape shape = new Circle(5.0);
        System.out.println("圆的面积: " + shape.getArea());
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：继承与构造方法
        System.out.println("填空1：继承与构造方法");
        System.out.println("代码：");
        System.out.println("  class Animal {");
        System.out.println("      String name;");
        System.out.println("      public Animal(String name) { this.name = name; }");
        System.out.println("  }");
        System.out.println("  class Dog extends Animal {");
        System.out.println("      public Dog(String name) {");
        System.out.println("          ______(name);  // 第一空：调用父类构造方法");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println("答案：第一空填 super\n");

        // 填空2：方法重写
        System.out.println("填空2：方法重写");
        System.out.println("代码：");
        System.out.println("  class Animal {");
        System.out.println("      public void eat() { System.out.println(\"吃东西\"); }");
        System.out.println("  }");
        System.out.println("  class Dog extends Animal {");
        System.out.println("      ______  // 第二空：重写eat方法");
        System.out.println("      public void eat() { System.out.println(\"啃骨头\"); }");
        System.out.println("  }");
        System.out.println("答案：第二空填 @Override\n");

        // 填空3：接口实现
        System.out.println("填空3：接口实现");
        System.out.println("代码：");
        System.out.println("  interface Flyable {");
        System.out.println("      void fly();");
        System.out.println("  }");
        System.out.println("  class Bird ______ Flyable {  // 第三空");
        System.out.println("      public void fly() { System.out.println(\"飞翔\"); }");
        System.out.println("  }");
        System.out.println("答案：第三空填 implements\n");

        // 实际运行
        System.out.println("【运行结果】");
        Animal dog = new Dog("旺财");
        dog.eat();
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个图形类层次结构");
        System.out.println("要求：");
        System.out.println("1. 抽象父类Shape，包含计算面积的抽象方法getArea()");
        System.out.println("2. 子类Circle（半径）、Rectangle（长、宽）继承Shape");
        System.out.println("3. 测试类中利用多态统一计算并打印面积\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("  abstract class Shape {");
        System.out.println("      public abstract double getArea();");
        System.out.println("  }");
        System.out.println("  class Circle extends Shape {");
        System.out.println("      private double radius;");
        System.out.println("      public Circle(double r) { this.radius = r; }");
        System.out.println("      public double getArea() { return Math.PI * radius * radius; }");
        System.out.println("  }");
        System.out.println("  class Rectangle extends Shape {");
        System.out.println("      private double width, height;");
        System.out.println("      public Rectangle(double w, double h) { this.width = w; this.height = h; }");
        System.out.println("      public double getArea() { return width * height; }");
        System.out.println("  }\n");

        // 实际运行
        System.out.println("【运行结果】");
        Shape[] shapes = {new Circle(5.0), new Rectangle(4, 6), new Circle(3.0)};
        for (Shape s : shapes) {
            System.out.printf("面积: %.2f%n", s.getArea());
        }
    }

    // 辅助方法
    public static double calculateArea(Shape shape) {
        return shape.getArea();
    }
}

// 辅助类（用于练习2填空题的实际运行）
class Shape {
    public double getArea() {
        return 0;
    }
}

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

// 辅助类（用于练习2填空题的实际运行）
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + "正在吃东西");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(name + "正在啃骨头");
    }
}
