# Java大学期末考试复习系统

本项目包含Java大学期末考试的全部核心知识点，每个章节都有详细的代码示例和练习题。

## 项目结构

```
期末/
├── src/
│   ├── chapter1/          # 第1章：Java基础
│   │   ├── DataTypeDemo.java    # 数据类型与变量
│   │   ├── FlowControlDemo.java # 流程控制
│   │   ├── ArrayDemo.java       # 数组
│   │   └── MethodDemo.java      # 方法
│   │
│   ├── chapter2/          # 第2章：面向对象
│   │   ├── ClassAndObjectDemo.java      # 类与对象
│   │   ├── InheritanceDemo.java         # 继承与多态
│   │   └── AbstractAndInterfaceDemo.java # 抽象类与接口
│   │
│   ├── chapter3/          # 第3章：常用Java API
│   │   ├── StringDemo.java        # String字符串
│   │   └── WrapperAndUtilDemo.java # 包装类与工具类
│   │
│   ├── chapter4/          # 第4章：集合与泛型
│   │   ├── CollectionDemo.java    # Collection集合
│   │   ├── MapDemo.java           # Map集合
│   │   └── GenericDemo.java       # 泛型
│   │
│   ├── chapter5/          # 第5章：I/O流
│   │   ├── IODemo.java            # 字节流与字符流
│   │   └── SerializeDemo.java     # 对象序列化
│   │
│   ├── chapter6/          # 第6章：多线程
│   │   ├── ThreadDemo.java        # 线程创建方式
│   │   ├── SynchronizedDemo.java  # 线程同步
│   │   └── ThreadPoolDemo.java    # 线程池
│   │
│   ├── chapter7/          # 第7章：网络编程
│   │   └── SocketDemo.java        # TCP Socket编程
│   │
│   ├── chapter8/          # 第8章：数据库编程
│   │   ├── JDBCDemo.java          # JDBC基础
│   │   └── ConnectionPoolDemo.java # 连接池
│   │
│   ├── chapter9/          # 第9章：反射机制
│   │   └── ReflectionDemo.java    # 反射基础
│   │
│   └── exercises/         # 练习题
│       ├── Chapter1Exercises.java  # 第1章练习题
│       ├── Chapter2Exercises.java  # 第2章练习题
│       ├── Chapter3Exercises.java  # 第3章练习题
│       ├── Chapter4Exercises.java  # 第4章练习题
│       ├── Chapter5Exercises.java  # 第5章练习题
│       ├── Chapter6Exercises.java  # 第6章练习题
│       ├── Chapter7Exercises.java  # 第7章练习题
│       ├── Chapter8Exercises.java  # 第8章练习题
│       └── Chapter9Exercises.java  # 第9章练习题
│
└── App.java              # 主程序入口
```

## 使用方法

### 方法1：运行主程序（推荐）
1. 打开IDEA，导入项目
2. 运行 `App.java`
3. 根据菜单选择要学习的章节

### 方法2：单独运行每个示例
1. 直接运行任意章节的 `.java` 文件
2. 每个文件都有 `main` 方法，可以独立运行

## 各章节内容

### 第1章：Java基础
- **DataTypeDemo.java**: 8种基本数据类型、自动/强制转换、常量
- **FlowControlDemo.java**: if-else、switch、for/while循环、break/continue
- **ArrayDemo.java**: 数组声明、初始化、遍历、常用操作、二维数组、Arrays工具类
- **MethodDemo.java**: 方法定义、重载、值传递、可变参数、递归

### 第2章：面向对象
- **ClassAndObjectDemo.java**: 类定义、构造方法、this关键字、封装
- **InheritanceDemo.java**: extends、super、方法重写、多态、instanceof
- **AbstractAndInterfaceDemo.java**: 抽象类、接口、implements、多实现

### 第3章：常用Java API
- **StringDemo.java**: String不可变性、常用方法、StringBuilder/StringBuffer
- **WrapperAndUtilDemo.java**: 包装类、自动装箱/拆箱、缓存机制、Math、Random、Date

### 第4章：集合与泛型
- **CollectionDemo.java**: List、Set、遍历方式、集合转换
- **MapDemo.java**: HashMap、TreeMap、LinkedHashMap、遍历方式
- **GenericDemo.java**: 泛型类、泛型方法、泛型接口、通配符、PECS原则

### 第5章：I/O流
- **IODemo.java**: 字节流、字符流、缓冲流、文件操作
- **SerializeDemo.java**: Serializable接口、transient关键字、序列化版本号

### 第6章：多线程
- **ThreadDemo.java**: 继承Thread、实现Runnable、Lambda表达式
- **SynchronizedDemo.java**: synchronized同步、Lock锁、死锁
- **ThreadPoolDemo.java**: ExecutorService、Callable/Future、线程池类型

### 第7章：网络编程
- **SocketDemo.java**: TCP服务器端、客户端、InetAddress

### 第8章：数据库编程
- **JDBCDemo.java**: JDBC步骤、PreparedStatement、事务
- **ConnectionPoolDemo.java**: 连接池原理、常用连接池

### 第9章：反射机制
- **ReflectionDemo.java**: Class对象、构造器、字段、方法、注解

## 练习题类型

每个章节的练习题包含：
1. **代码改错题**: 找出并修复代码中的错误
2. **填空题**: 补全代码使其正确运行
3. **设计题**: 根据需求设计代码实现

## 注意事项

1. 数据库相关章节需要MySQL环境，请根据实际情况修改数据库配置
2. 网络编程章节的服务器端和客户端需要分别运行
3. 建议按照章节顺序学习，循序渐进

## 技术栈

- Java 17+
- MySQL 8.0+（数据库章节需要）
- JDBC驱动

## 联系方式

如有问题，请联系老师或同学。
