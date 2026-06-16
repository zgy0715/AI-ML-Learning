package chapter9;

import java.lang.reflect.*;
import java.lang.annotation.*;

/**
 * 第9章：反射机制
 * 期末考试重点：Class对象、获取构造器、访问字段、调用方法、注解
 */
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. 获取Class对象的三种方式 ===");
        // 方式1：Class.forName()
        Class<?> clazz1 = Class.forName("java.lang.String");
        System.out.println("方式1: " + clazz1.getName());

        // 方式2：对象.getClass()
        String str = "Hello";
        Class<?> clazz2 = str.getClass();
        System.out.println("方式2: " + clazz2.getName());

        // 方式3：类.class
        Class<?> clazz3 = String.class;
        System.out.println("方式3: " + clazz3.getName());

        System.out.println("\n=== 2. 获取类信息 ===");
        Class<?> personClass = Class.forName("chapter9.Person");

        // 获取类名
        System.out.println("类名: " + personClass.getName());
        System.out.println("简单类名: " + personClass.getSimpleName());

        // 获取父类
        Class<?> superClass = personClass.getSuperclass();
        System.out.println("父类: " + superClass.getName());

        // 获取接口
        Class<?>[] interfaces = personClass.getInterfaces();
        System.out.println("实现的接口:");
        for (Class<?> iface : interfaces) {
            System.out.println("  " + iface.getName());
        }

        System.out.println("\n=== 3. 创建对象 ===");
        // 使用无参构造器
        Object person1 = personClass.getDeclaredConstructor().newInstance();
        System.out.println("无参构造: " + person1);

        // 使用有参构造器
        Constructor<?> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        Object person2 = constructor.newInstance("张三", 25);
        System.out.println("有参构造: " + person2);

        System.out.println("\n=== 4. 访问和修改字段 ===");
        // 获取所有字段
        Field[] fields = personClass.getDeclaredFields();
        System.out.println("所有字段:");
        for (Field field : fields) {
            System.out.println("  " + field.getName() + " (" + field.getType().getSimpleName() + ")");
        }

        // 访问私有字段
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);  // 允许访问私有字段
        String name = (String) nameField.get(person2);
        System.out.println("获取私有字段name: " + name);

        // 修改字段值
        nameField.set(person2, "李四");
        System.out.println("修改后: " + person2);

        System.out.println("\n=== 5. 调用方法 ===");
        // 获取所有方法
        Method[] methods = personClass.getDeclaredMethods();
        System.out.println("所有方法:");
        for (Method method : methods) {
            System.out.println("  " + method.getName() + "()");
        }

        // 调用public方法
        Method sayHelloMethod = personClass.getMethod("sayHello");
        sayHelloMethod.invoke(person2);

        // 调用私有方法
        Method privateMethod = personClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(person2);

        // 调用带参数的方法
        Method addMethod = personClass.getMethod("add", int.class, int.class);
        int result = (int) addMethod.invoke(person2, 10, 20);
        System.out.println("10 + 20 = " + result);

        System.out.println("\n=== 6. 反射的应用场景 ===");
        System.out.println("1. 框架设计（Spring IoC）");
        System.out.println("2. IDE自动提示");
        System.out.println("3. 动态代理");
        System.out.println("4. 单元测试（JUnit）");
        System.out.println("5. 注解处理");

        System.out.println("\n=== 7. 注解 ===");
        // 获取类上的注解
        if (personClass.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = personClass.getAnnotation(MyAnnotation.class);
            System.out.println("类注解: " + annotation.value());
        }

        // 获取方法上的注解
        Method annotatedMethod = personClass.getMethod("annotatedMethod");
        if (annotatedMethod.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = annotatedMethod.getAnnotation(MyAnnotation.class);
            System.out.println("方法注解: " + annotation.value());
        }
    }
}

/**
 * 示例类
 */
class Person implements Runnable {
    private String name;
    private int age;
    protected String major;

    // 无参构造器
    public Person() {
        this.name = "未知";
        this.age = 0;
    }

    // 有参构造器
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // public方法
    public void sayHello() {
        System.out.println("Hello, 我是 " + name);
    }

    // 带参数的方法
    public int add(int a, int b) {
        return a + b;
    }

    // 私有方法
    private void privateMethod() {
        System.out.println("这是一个私有方法");
    }

    // 带注解的方法
    @MyAnnotation("这是一个带注解的方法")
    public void annotatedMethod() {
        System.out.println("带注解的方法");
    }

    @Override
    public void run() {
        System.out.println("实现Runnable接口的run方法");
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface MyAnnotation {
    String value() default "";
}
