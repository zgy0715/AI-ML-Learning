# 第2课：面向对象编程（OOP）

## 一、类与对象

### 什么是类？什么是对象？

```
类（Class） = 图纸/模板    →  描述"什么东西有什么属性和行为"
对象（Object）= 实际的物品  →  根据图纸造出来的具体东西

举例：
┌─────────────────────────────────────────────┐
│  类：汽车（Car）                              │
│  ├── 属性：颜色、品牌、速度                    │
│  └── 行为：启动、加速、刹车                    │
│                                              │
│  对象：                                        │
│  ├── 我的车（红色，宝马，当前速度60）           │
│  └── 你的车（白色，奔驰，当前速度0）            │
└─────────────────────────────────────────────┘
```

### 定义一个类

```java
public class Student {
    // 属性（成员变量）
    String name;       // 姓名
    int age;           // 年龄
    double score;      // 成绩

    // 方法（行为）
    public void study() {
        System.out.println(name + "正在学习");
    }

    public void showInfo() {
        System.out.println("姓名：" + name + "，年龄：" + age + "，成绩：" + score);
    }
}
```

### 创建和使用对象

```java
// 用 new 关键字创建对象
Student s1 = new Student();
s1.name = "张三";      // 给属性赋值
s1.age = 20;
s1.score = 95.5;
s1.showInfo();         // 调用方法

Student s2 = new Student();
s2.name = "李四";
s2.age = 21;
s2.score = 88.0;
s2.showInfo();
```

---

## 二、构造方法

构造方法在创建对象时**自动调用**，用来初始化属性。

```java
public class Student {
    String name;
    int age;
    double score;

    // 无参构造（默认）
    public Student() {
        this.name = "未知";
        this.age = 0;
        this.score = 0;
    }

    // 有参构造
    public Student(String name, int age, double score) {
        this.name = name;       // this.当前对象的属性 = 参数
        this.age = age;
        this.score = score;
    }
}

// 使用
Student s1 = new Student();                      // 调用无参构造
Student s2 = new Student("张三", 20, 95.5);      // 调用有参构造
```

> 💡 `this` 关键字：指向**当前对象**，用来区分同名的属性和参数。

### 构造方法重载

一个类可以有多个构造方法（参数不同），这就是**构造方法重载**：

```java
public class Student {
    String name;
    int age;

    public Student() {
        this("未知", 0);   // 调用另一个构造方法（必须放第一行）
    }

    public Student(String name) {
        this(name, 18);    // 调用两个参数的构造
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 三、封装

封装就是**把属性藏起来，只通过方法访问**，防止外部乱改数据。

```java
public class BankAccount {
    // private：私有的，只能在本类中访问
    private double balance;    // 余额

    // 通过公共方法访问（getter/setter）
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {    // 存款
        if (amount > 0) {
            balance += amount;
            System.out.println("存入" + amount + "元，余额：" + balance);
        }
    }

    public void withdraw(double amount) {   // 取款
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取出" + amount + "元，余额：" + balance);
        } else {
            System.out.println("余额不足！");
        }
    }
}

// 使用
BankAccount account = new BankAccount();
// account.balance = -1000;    // ❌ 编译错误！private不能直接访问
account.deposit(1000);         // ✅ 通过方法操作
account.withdraw(500);         // ✅ 有验证逻辑
```

### 访问修饰符（重要！）

```
┌────────────┬───────┬─────────┬──────────┬───────────┐
│ 修饰符      │ 本类   │ 同包     │ 子类      │ 其他包     │
├────────────┼───────┼─────────┼──────────┼───────────┤
│ private    │  ✅   │  ❌     │  ❌      │  ❌       │
│ 默认(不写)  │  ✅   │  ✅     │  ❌      │  ❌       │
│ protected  │  ✅   │  ✅     │  ✅      │  ❌       │
│ public     │  ✅   │  ✅     │  ✅      │  ✅       │
└────────────┴───────┴─────────┴──────────┴───────────┘

考试常考：private（私有）和 public（公共）最常用
```

---

## 四、继承

子类**继承**父类的属性和方法，实现代码复用。

```java
// 父类（基类）
public class Animal {
    String name;
    int age;

    public void eat() {
        System.out.println(name + "在吃东西");
    }

    public void sleep() {
        System.out.println(name + "在睡觉");
    }
}

// 子类（派生类）用 extends 继承
public class Dog extends Animal {
    String breed;   // 子类特有属性

    public void bark() {   // 子类特有方法
        System.out.println(name + "汪汪叫");
    }
}

// 使用
Dog dog = new Dog();
dog.name = "旺财";     // ✅ 继承了父类的属性
dog.eat();             // ✅ 继承了父类的方法
dog.bark();            // ✅ 子类自己的方法
```

### 方法重写（Override）

子类可以**重写**父类的方法，改变其行为：

```java
public class Animal {
    public void makeSound() {
        System.out.println("动物发出声音");
    }
}

public class Dog extends Animal {
    @Override   // 注解，表示重写了父类方法（建议加上）
    public void makeSound() {
        System.out.println("汪汪汪！");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("喵喵喵！");
    }
}

// 多态的体现
Animal a1 = new Dog();
Animal a2 = new Cat();
a1.makeSound();   // 汪汪汪！（调用Dog的版本）
a2.makeSound();   // 喵喵喵！（调用Cat的版本）
```

> ⚠️ **重写 vs 重载：**
> - **重写（Override）**：子类重写父类**同名同参数**的方法
> - **重载（Overload）**：同一个类中，**同名不同参数**的方法

### super关键字

```java
public class Dog extends Animal {
    @Override
    public void eat() {
        super.eat();    // 先调用父类的eat方法
        System.out.println("然后喝水");    // 再执行自己的逻辑
    }
}
```

### Object类（所有类的父类）

Java中**所有类都继承自Object类**（即使你不写extends）：

```java
// Object类的常用方法：
// toString()  → 返回对象的字符串表示
// equals()    → 判断两个对象是否相等
// hashCode()  → 返回哈希码

public class Student {
    String name;

    @Override
    public String toString() {
        return "Student{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return this.name.equals(other.name);
    }
}
```

---

## 五、多态

多态就是**同一个方法，不同对象有不同的行为**。

```
多态三要素：
1. 有继承关系
2. 子类重写父类方法
3. 父类引用指向子类对象

Animal a = new Dog();   // ✅ 父类引用指向子类对象
a.makeSound();          // 调用的是Dog的makeSound()
```

### 向上转型和向下转型

```java
// 向上转型（自动）：子类 → 父类
Animal a = new Dog();    // 自动转型，a只能调用Animal中定义的方法

// 向下转型（强制）：父类 → 子类
Dog d = (Dog) a;         // 需要强制转换
d.bark();                // 现在可以调用Dog特有的方法

// ⚠️ ClassCastException：类型转换错误
// Animal a = new Cat();
// Dog d = (Dog) a;     // ❌ 运行时错误！Cat不能转成Dog

// 用 instanceof 安全判断
if (a instanceof Dog) {
    Dog d2 = (Dog) a;
    d2.bark();
}
```

---

## 六、抽象类

抽象类**不能直接创建对象**，只能被继承。抽象方法**没有方法体**，子类必须实现。

```java
// 抽象类：用 abstract 修饰
public abstract class Shape {
    String color;

    // 普通方法（可以有方法体）
    public void display() {
        System.out.println("这是一个" + color + "的图形");
    }

    // 抽象方法（没有方法体，子类必须实现）
    public abstract double area();       // 面积
    public abstract double perimeter();  // 周长
}

// 子类必须实现所有抽象方法
public class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
        this.color = "红色";
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Shape s = new Shape();   // ❌ 抽象类不能创建对象
Circle c = new Circle(5);   // ✅ 可以创建子类对象
System.out.println("面积：" + c.area());
```

---

## 七、接口

接口是一种**特殊的抽象类**，只有常量和抽象方法（Java 8后可以有默认方法）。

```java
// 定义接口：用 interface 关键字
public interface Flyable {
    // 常量（默认 public static final）
    int MAX_HEIGHT = 10000;

    // 抽象方法（默认 public abstract）
    void fly();     // 飞行
    void land();    // 着陆
}

public interface Swimmable {
    void swim();
}

// 一个类可以实现多个接口（用 implements）
public class Duck extends Animal implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("鸭子在飞");
    }

    @Override
    public void land() {
        System.out.println("鸭子降落");
    }

    @Override
    public void swim() {
        System.out.println("鸭子在游泳");
    }
}

// 接口也可以继承接口
public interface SuperDuck extends Flyable, Swimmable {
    void superPower();   // 超级能力
}
```

### 抽象类 vs 接口（必考！）

```
┌──────────────┬──────────────────┬──────────────────┐
│ 特性          │ 抽象类            │ 接口              │
├──────────────┼──────────────────┼──────────────────┤
│ 关键字        │ abstract class   │ interface        │
│ 继承/实现      │ extends（单继承） │ implements（多实现）│
│ 构造方法      │ 有               │ 没有              │
│ 成员变量      │ 任意类型          │ 只有常量           │
│ 方法          │ 可以有普通方法     │ 只有抽象方法        │
│              │                  │ （Java8后有默认方法）│
│ 关系          │ is-a（是一种）    │ can-do（能做什么）  │
│ 例子          │ 狗 is a Animal   │ 鸭 can fly         │
└──────────────┴──────────────────┴──────────────────┘
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 类 | class | 创建对象的模板 |
| 对象 | object | 类的实例 |
| 构造方法 | constructor | 创建对象时自动调用的方法 |
| 封装 | encapsulation | 隐藏内部细节，只暴露必要接口 |
| 继承 | inheritance | 子类获得父类的属性和方法 |
| 多态 | polymorphism | 同一方法，不同对象有不同行为 |
| 重写 | override | 子类重写父类同名同参数方法 |
| 重载 | overload | 同类中同名不同参数的方法 |
| 抽象类 | abstract class | 不能直接创建对象的类 |
| 接口 | interface | 特殊的抽象类，支持多实现 |
| this | this | 当前对象的引用 |
| super | super | 父类的引用 |
| instanceof | instanceof | 判断对象是否是某个类的实例 |

---

> **下一课预告：** 第3课我们将学习Java常用API——String字符串处理、包装类、Math数学工具、日期处理和异常处理。
