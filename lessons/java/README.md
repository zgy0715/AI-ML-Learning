# Java期末复习课程

## 课程概览

本课程专为大学Java期末考试设计，共7课，覆盖所有核心考点。

## 课程目录

| 课号 | 主题 | 文件 | 核心考点 |
|------|------|------|----------|
| 第1课 | Java基础入门 | lesson_1_java_basics.md + Lesson1_Basics.java | 变量、数据类型、运算符、控制流程、数组、方法 |
| 第2课 | 面向对象编程 | lesson_2_oop.md + Lesson2_OOP.java | 类、对象、继承、多态、封装、抽象类、接口 |
| 第3课 | Java常用API | lesson_3_java_api.md + Lesson3_JavaAPI.java | String、包装类、Math、异常处理 |
| 第4课 | 集合与泛型 | lesson_4_collections.md + Lesson4_Collections.java | ArrayList、HashSet、HashMap、Iterator、泛型 |
| 第5课 | I/O流与文件 | lesson_5_io.md + Lesson5_IO.java | 字节流、字符流、缓冲流、序列化 |
| 第6课 | 多线程与网络 | lesson_6_thread_network.md + Lesson6_ThreadNetwork.java | Thread、synchronized、Socket、TCP/UDP |
| 第7课 | 反射机制 | lesson_7_reflection.md + Lesson7_Reflection.java | Class、Constructor、Method、Field |

## 文件结构

```
lessons/java/
├── README.md                      # 本文件
├── lesson_1_java_basics.md        # 第1课理论
├── Lesson1_Basics.java            # 第1课代码
├── lesson_2_oop.md                # 第2课理论
├── Lesson2_OOP.java               # 第2课代码
├── lesson_3_java_api.md           # 第3课理论
├── Lesson3_JavaAPI.java           # 第3课代码
├── lesson_4_collections.md        # 第4课理论
├── Lesson4_Collections.java        # 第4课代码
├── lesson_5_io.md                 # 第5课理论
├── Lesson5_IO.java                # 第5课代码
├── lesson_6_thread_network.md     # 第6课理论
├── Lesson6_ThreadNetwork.java     # 第6课代码
├── lesson_7_reflection.md         # 第7课理论
└── Lesson7_Reflection.java        # 第7课代码
```

## 如何使用

### 理论学习
1. 按顺序阅读每课的 `.md` 文件
2. 重点看表格对比、代码示例和术语表

### 代码练习
```bash
# 进入java课程目录
cd lessons/java

# 编译
javac Lesson1_Basics.java

# 运行
java Lesson1_Basics
```

### 考试重点
- **第2课 OOP**：继承、多态、抽象类vs接口（必考大题）
- **第3课 异常处理**：try-catch-finally（选择题+编程题）
- **第4课 集合**：ArrayList、HashMap的使用（编程题）
- **第6课 多线程**：Thread、synchronized（选择题+简答）
- **第7课 反射**：了解即可（选择题）

## 与Python课程的对比

| 概念 | Python | Java |
|------|--------|------|
| 变量声明 | 自动推断 | 必须声明类型 |
| 代码块 | 缩进 | {}花括号 |
| 语句结尾 | 无 | 分号; |
| 面向对象 | class | class（更严格） |
| 集合 | list/dict/set | ArrayList/HashMap/HashSet |
| 异常 | try/except | try/catch |
| 文件 | open() | FileInputStream/FileReader |

## 图片资源

PNG图片统一放在 `data/java/` 目录下。
