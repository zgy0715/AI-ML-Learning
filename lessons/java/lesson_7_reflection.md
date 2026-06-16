# 第7课：反射机制

## 一、什么是反射？

反射就是**在运行时动态获取类的信息、创建对象、调用方法**。

```
正常方式：编译时就知道要调用什么
  Student s = new Student();
  s.study();

反射方式：运行时才知道要调用什么
  Class<?> clazz = Class.forName("Student");
  Object obj = clazz.newInstance();
  Method m = clazz.getMethod("study");
  m.invoke(obj);

用途：
├── 框架开发（Spring、MyBatis等都用反射）
├── 动态加载类
├── 访问私有成员
└── 注解处理
```

---

## 二、获取Class对象

每个类在JVM中只有一个Class对象，它是反射的入口。

```java
// 三种方式获取Class对象
// 方式1：Class.forName()（最常用，适合框架）
Class<?> clazz1 = Class.forName("java.lang.String");

// 方式2：.class（编译时确定）
Class<?> clazz2 = String.class;

// 方式3：getClass()（已有对象时）
String s = "hello";
Class<?> clazz3 = s.getClass();

// 三种方式获取的是同一个Class对象
System.out.println(clazz1 == clazz2);  // true
System.out.println(clazz2 == clazz3);  // true
```

---

## 三、获取类信息

```java
Class<?> clazz = Class.forName("java.lang.String");

// 类名
clazz.getName();           // 完整类名：java.lang.String
clazz.getSimpleName();     // 简单类名：String
clazz.getCanonicalName();  // 规范名：java.lang.String

// 父类
Class<?> superClass = clazz.getSuperclass();
System.out.println("父类: " + superClass.getSimpleName());  // Object

// 接口
Class<?>[] interfaces = clazz.getInterfaces();
for (Class<?> i : interfaces) {
    System.out.println("实现接口: " + i.getSimpleName());
}

// 修饰符
int modifiers = clazz.getModifiers();
System.out.println("是公共类: " + Modifier.isPublic(modifiers));
```

---

## 四、动态创建对象

```java
// 方式1：newInstance()（无参构造）
Class<?> clazz = Class.forName("java.util.ArrayList");
Object obj = clazz.newInstance();    // 等价于 new ArrayList()

// 方式2：通过构造器（支持有参构造）
Constructor<?>[] constructors = clazz.getConstructors();
// 找到无参构造
Constructor<?> constructor = clazz.getConstructor();
Object obj2 = constructor.newInstance();

// 找到有参构造
Constructor<?> strConstructor = String.class.getConstructor(String.class);
Object str = strConstructor.newInstance("Hello");
System.out.println(str);  // Hello
```

---

## 五、访问和修改字段

```java
public class Student {
    public String name;
    private int age;
    private static String school;
}

Class<?> clazz = Student.class;

// 获取公共字段
Field nameField = clazz.getField("name");
Student s = (Student) clazz.newInstance();
nameField.set(s, "张三");           // 设置值
System.out.println(nameField.get(s)); // 获取值

// 获取私有字段（重要！）
Field ageField = clazz.getDeclaredField("age");
ageField.setAccessible(true);   // ⚠️ 突破private限制
ageField.set(s, 20);
System.out.println(ageField.get(s));

// 获取静态字段
Field schoolField = clazz.getDeclaredField("school");
schoolField.setAccessible(true);
schoolField.set(null, "清华大学");  // 静态字段第一个参数传null
System.out.println(schoolField.get(null));
```

---

## 六、调用方法

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    public static String greet(String name) {
        return "你好，" + name;
    }
}

Class<?> clazz = Calculator.class;
Object obj = clazz.newInstance();

// 获取公共方法
Method addMethod = clazz.getMethod("add", int.class, int.class);
int result = (int) addMethod.invoke(obj, 3, 5);   // 调用add(3, 5)
System.out.println("3 + 5 = " + result);

// 获取私有方法
Method mulMethod = clazz.getDeclaredMethod("multiply", int.class, int.class);
mulMethod.setAccessible(true);   // 突破private
int result2 = (int) mulMethod.invoke(obj, 3, 5);
System.out.println("3 * 5 = " + result2);

// 调用静态方法
Method greetMethod = clazz.getMethod("greet", String.class);
String greeting = (String) greetMethod.invoke(null, "世界");  // 静态方法传null
System.out.println(greeting);
```

---

## 七、获取构造方法

```java
Class<?> clazz = Student.class;

// 获取所有公共构造方法
Constructor<?>[] constructors = clazz.getConstructors();

// 获取所有构造方法（包括私有）
Constructor<?>[] allConstructors = clazz.getDeclaredConstructors();

// 获取指定构造方法
Constructor<?> noArg = clazz.getConstructor();              // 无参
Constructor<?> withArg = clazz.getConstructor(String.class, int.class);  // 有参

// 用构造器创建对象
Student s = (Student) withArg.newInstance("张三", 20);
```

---

## 八、反射的实际应用

### 简单的依赖注入框架

```java
// 模拟Spring的@Autowired
public class UserService {
    @Autowired
    private UserDao userDao;   // 通过反射注入，不用new

    public void saveUser(String name) {
        userDao.save(name);
    }
}

// 反射实现注入
public static void inject(Object obj) throws Exception {
    Class<?> clazz = obj.getClass();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
        if (field.isAnnotationPresent(Autowired.class)) {
            field.setAccessible(true);
            // 创建依赖对象
            Object dependency = field.getType().newInstance();
            field.set(obj, dependency);   // 注入
        }
    }
}
```

### 通用的toString方法

```java
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
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 反射 | reflection | 运行时动态获取类信息 |
| Class对象 | Class object | 类的元数据 |
| 字段 | field | 类的属性 |
| 方法 | method | 类的行为 |
| 构造器 | constructor | 创建对象的方法 |
| 注解 | annotation | 元数据标记 |
| setAccessible | setAccessible | 突破private限制 |

---

> **恭喜！** 你已经完成了Java期末复习的全部课程！建议结合代码多练习，特别是OOP、集合、异常处理和多线程这几个考试重点。
