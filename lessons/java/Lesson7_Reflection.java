/**
 * 第7课：反射机制
 * 包含：Class对象、获取类信息、动态创建对象、访问字段、调用方法
 *
 * 编译运行：
 *   javac Lesson7_Reflection.java
 *   java Lesson7_Reflection
 */

import java.lang.reflect.*;

// 测试用的类
class Person {
    public String name;
    private int age;
    private static String species = "人类";

    public Person() {
        this.name = "未知";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
        this.age = 0;
    }

    public void sayHello() {
        System.out.println(name + "说：你好！");
    }

    public void sayHello(String to) {
        System.out.println(name + "对" + to + "说：你好！");
    }

    private int calculate(int a, int b) {
        return a * b;
    }

    public static String getInfo() {
        return "我是一个人";
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public static String getSpecies() { return species; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

class StudentRef extends Person {
    public String school;

    public StudentRef() {
        super();
        this.school = "未知学校";
    }

    public StudentRef(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public void study() {
        System.out.println(name + "在" + school + "学习");
    }
}

public class Lesson7_Reflection {

    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void printSection(String title) {
        System.out.println("\n" + title);
        System.out.println(repeat("-", 30));
    }

    // ========== Part 1: 获取Class对象 ==========
    public static void classObjectDemo() throws ClassNotFoundException {
        printSection("1. 获取Class对象（三种方式）");

        // 方式1：Class.forName()
        Class<?> clazz1 = Class.forName("java.lang.String");
        System.out.println("Class.forName: " + clazz1.getName());

        // 方式2：.class
        Class<?> clazz2 = String.class;
        System.out.println(".class: " + clazz2.getName());

        // 方式3：getClass()
        String s = "hello";
        Class<?> clazz3 = s.getClass();
        System.out.println("getClass(): " + clazz3.getName());

        // 验证是同一个对象
        System.out.println("\n三种方式获取的是同一个Class对象：");
        System.out.println("clazz1 == clazz2: " + (clazz1 == clazz2));
        System.out.println("clazz2 == clazz3: " + (clazz2 == clazz3));
    }

    // ========== Part 2: 获取类信息 ==========
    public static void classInfoDemo() throws ClassNotFoundException {
        printSection("2. 获取类信息");

        Class<?> clazz = Class.forName("Lesson7_Reflection$StudentRef");

        // 类名
        System.out.println("完整类名: " + clazz.getName());
        System.out.println("简单类名: " + clazz.getSimpleName());

        // 父类
        Class<?> superClass = clazz.getSuperclass();
        System.out.println("父类: " + superClass.getSimpleName());

        // 接口
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("实现接口数: " + interfaces.length);

        // 修饰符
        int modifiers = clazz.getModifiers();
        System.out.println("是公共类: " + Modifier.isPublic(modifiers));
        System.out.println("是抽象类: " + Modifier.isAbstract(modifiers));

        // 获取所有公共方法
        System.out.println("\n公共方法：");
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println("  " + m.getReturnType().getSimpleName() + " " + m.getName() + "()");
        }

        // 获取所有字段
        System.out.println("\n字段：");
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " " +
                f.getType().getSimpleName() + " " + f.getName());
        }

        // 获取所有构造器
        System.out.println("\n构造器：");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println("  " + c);
        }
    }

    // ========== Part 3: 动态创建对象 ==========
    public static void createObjectDemo() throws Exception {
        printSection("3. 动态创建对象");

        Class<?> clazz = Class.forName("Person");

        // 方式1：无参构造
        Object obj1 = clazz.newInstance();
        System.out.println("无参构造: " + obj1);

        // 方式2：通过构造器
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println("构造器: " + c);
        }

        // 有参构造
        Constructor<?> withArgs = clazz.getConstructor(String.class, int.class);
        Object obj2 = withArgs.newInstance("张三", 20);
        System.out.println("有参构造: " + obj2);

        // 私有构造
        Constructor<?> privateCtor = clazz.getDeclaredConstructor(String.class);
        privateCtor.setAccessible(true);
        Object obj3 = privateCtor.newInstance("李四");
        System.out.println("私有构造: " + obj3);
    }

    // ========== Part 4: 访问和修改字段 ==========
    public static void fieldDemo() throws Exception {
        printSection("4. 访问和修改字段");

        Class<?> clazz = Person.class;
        Person p = (Person) clazz.newInstance();

        // 公共字段
        Field nameField = clazz.getField("name");
        nameField.set(p, "张三");
        System.out.println("公共字段name: " + nameField.get(p));

        // 私有字段
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);   // 突破private
        ageField.set(p, 25);
        System.out.println("私有字段age: " + ageField.get(p));

        // 静态字段
        Field speciesField = clazz.getDeclaredField("species");
        speciesField.setAccessible(true);
        System.out.println("静态字段species: " + speciesField.get(null));
        speciesField.set(null, "智人");
        System.out.println("修改后species: " + Person.getSpecies());
    }

    // ========== Part 5: 调用方法 ==========
    public static void methodDemo() throws Exception {
        printSection("5. 调用方法");

        Class<?> clazz = Person.class;
        Person p = new Person("王五", 22);

        // 公共方法（无参）
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(p);

        // 公共方法（有参）
        Method sayHelloTo = clazz.getMethod("sayHello", String.class);
        sayHelloTo.invoke(p, "世界");

        // 私有方法
        Method calculate = clazz.getDeclaredMethod("calculate", int.class, int.class);
        calculate.setAccessible(true);
        int result = (int) calculate.invoke(p, 3, 5);
        System.out.println("私有方法calculate(3, 5) = " + result);

        // 静态方法
        Method getInfo = clazz.getMethod("getInfo");
        String info = (String) getInfo.invoke(null);
        System.out.println("静态方法getInfo(): " + info);

        // 获取方法信息
        System.out.println("\n方法信息：");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("  " + Modifier.toString(m.getModifiers()) + " " +
                m.getReturnType().getSimpleName() + " " + m.getName());
            Class<?>[] params = m.getParameterTypes();
            System.out.println("    参数: ");
            for (Class<?> param : params) {
                System.out.println("      " + param.getSimpleName());
            }
        }
    }

    // ========== Part 6: 反射的应用 ==========
    // 通用的toString方法
    public static String reflectionToString(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName()).append("{");

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append(fields[i].getName()).append("=").append(fields[i].get(obj));
            if (i < fields.length - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    // 通用的比较方法
    public static boolean reflectionEquals(Object obj1, Object obj2) throws Exception {
        if (obj1 == null || obj2 == null) return obj1 == obj2;
        if (obj1.getClass() != obj2.getClass()) return false;

        Class<?> clazz = obj1.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);
            Object v2 = f.get(obj2);
            if (v1 == null) {
                if (v2 != null) return false;
            } else if (!v1.equals(v2)) {
                return false;
            }
        }
        return true;
    }

    public static void applicationDemo() throws Exception {
        printSection("6. 反射的实际应用");

        // 通用toString
        Person p1 = new Person("张三", 20);
        Person p2 = new Person("张三", 20);
        StudentRef s1 = new StudentRef("李四", 21, "清华大学");

        System.out.println("通用toString：");
        System.out.println("  " + reflectionToString(p1));
        System.out.println("  " + reflectionToString(s1));

        // 通用比较
        System.out.println("\n通用equals：");
        System.out.println("  p1 == p2? " + reflectionEquals(p1, p2));
        System.out.println("  p1 == p1? " + reflectionEquals(p1, p1));
    }

    // ========== 主方法 ==========
    public static void main(String[] args) throws Exception {
        System.out.println(repeat("=", 50));
        System.out.println("第7课：反射机制");
        System.out.println(repeat("=", 50));

        classObjectDemo();
        classInfoDemo();
        createObjectDemo();
        fieldDemo();
        methodDemo();
        applicationDemo();

        System.out.println("\n" + repeat("=", 50));
        System.out.println("第7课总结：");
        System.out.println("1. Class对象是反射的入口，三种获取方式");
        System.out.println("2. 可以在运行时获取类的所有信息");
        System.out.println("3. 动态创建对象：newInstance()或构造器");
        System.out.println("4. 访问私有成员需要setAccessible(true)");
        System.out.println("5. 反射是框架的基础，理解即可");
        System.out.println(repeat("=", 50));

        System.out.println("\n🎉 恭喜完成Java期末复习全部课程！");
        System.out.println("建议复习重点：");
        System.out.println("  1. OOP三大特性：封装、继承、多态");
        System.out.println("  2. 异常处理：try-catch-finally");
        System.out.println("  3. 集合框架：ArrayList、HashMap");
        System.out.println("  4. 多线程：Thread、synchronized");
        System.out.println("  5. I/O流：BufferedReader按行读取");
    }
}
