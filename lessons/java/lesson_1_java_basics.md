# 第1课：Java基础入门

## 一、Java是什么？

Java是一种**面向对象**的编程语言，由Sun Microsystems（现Oracle）于1995年发布。

```
Java的核心理念：
┌─────────────────────────────────────────────┐
│  "Write Once, Run Anywhere"                 │
│  （一次编写，到处运行）                        │
│                                             │
│  Java源代码 → 编译器 → 字节码(.class)         │
│       → JVM（Java虚拟机）→ 在任何平台运行      │
└─────────────────────────────────────────────┘
```

### Java vs Python 对比

| 特性 | Java | Python |
|------|------|--------|
| 类型系统 | **强类型**（必须声明变量类型） | 弱类型（自动推断） |
| 编译方式 | 先编译再运行（.class字节码） | 解释执行 |
| 入口 | `public static void main(String[] args)` | `if __name__ == "__main__"` |
| 代码块 | 用 `{}` 花括号 | 用缩进 |
| 语句结尾 | 必须用 `;` 分号 | 不需要 |
| 大小写 | **严格区分** | 严格区分 |

### 第一个Java程序

```java
// 文件名必须和类名一致：Hello.java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**编译和运行：**
```bash
javac Hello.java    # 编译，生成 Hello.class
java Hello          # 运行（注意：不带.class后缀）
```

> 💡 **和Python对比：** Python直接 `python hello.py` 就能运行，Java需要先编译再运行两步。

---

## 二、变量与数据类型

### 变量声明

Java是**强类型语言**，每个变量必须先声明类型，再赋值：

```java
// Python写法：name = "张三"     （不需要声明类型）
// Java写法：
String name = "张三";       // 字符串
int age = 20;               // 整数
double score = 95.5;        // 小数
boolean passed = true;      // 布尔值
char grade = 'A';           // 单个字符（注意是单引号）
```

### 基本数据类型（背！）

```
┌──────────┬──────────┬──────────────┬───────────────────┐
│ 类型      │ 关键字    │ 大小          │ 取值范围            │
├──────────┼──────────┼──────────────┼───────────────────┤
│ 字节型    │ byte     │ 1字节(8位)    │ -128 ~ 127         │
│ 短整型    │ short    │ 2字节(16位)   │ -32768 ~ 32767     │
│ 整型      │ int      │ 4字节(32位)   │ 约±21亿            │
│ 长整型    │ long     │ 8字节(64位)   │ 非常大              │
│ 单精度    │ float    │ 4字节(32位)   │ 约6-7位有效数字      │
│ 双精度    │ double   │ 8字节(64位)   │ 约15位有效数字       │
│ 字符型    │ char     │ 2字节(16位)   │ 0 ~ 65535          │
│ 布尔型    │ boolean  │ 1位           │ true / false        │
└──────────┴──────────┴──────────────┴───────────────────┘
```

```java
// long类型要加L后缀
long big = 10000000000L;

// float类型要加F后缀
float pi = 3.14F;

// double是默认小数类型
double d = 3.14;    // 这是double，不是float

// char用单引号，String用双引号
char c = '中';      // 单个字符
String s = "中国";   // 字符串（不是基本类型，是引用类型）
```

### 类型转换

```java
// 自动转换（小→大，安全）
int a = 100;
double b = a;        // int → double，自动转换

// 强制转换（大→小，可能丢失精度）
double x = 3.99;
int y = (int) x;     // y = 3，小数部分直接截断（不是四舍五入！）

// 字符串和数字互转
String numStr = "123";
int num = Integer.parseInt(numStr);     // 字符串→整数
String str = String.valueOf(num);       // 整数→字符串
```

> 💡 **和Python对比：** Python的 `int("123")` 就能转，Java要用 `Integer.parseInt()`，麻烦一些。

---

## 三、运算符

和Python基本一样，但有几个区别：

```java
// 算术运算符：+ - * / %
int result = 10 / 3;        // 结果是 3（整数除法，直接截断！）
double result2 = 10.0 / 3;  // 结果是 3.3333...（有一个是double就行）
int mod = 10 % 3;           // 结果是 1（取余）

// ⚠️ Java的 / 和Python不一样！
// Python: 10 / 3 = 3.333...  （自动变float）
// Java:   10 / 3 = 3          （整数除整数=整数）

// 比较运算符：== != > < >= <=
// ⚠️ Java用 == 比较值，没有Python的 is
// ⚠️ Java的equals() 才相当于Python的 ==（比较字符串内容）

// 逻辑运算符：&& || !
// Python写法：and  or  not
// Java写法：  &&   ||  !
```

```java
// 自增自减（Java特有，Python没有）
int i = 5;
i++;    // i变为6，等价于 i = i + 1
i--;    // i变为5，等价于 i = i - 1

// 前置和后置的区别（考试常考！）
int a = 5;
int b = a++;    // b=5, a=6 （先用后加）
int c = ++a;    // a=7, c=7 （先加后用）
```

---

## 四、控制流程

### if-else

```java
// 和Python对比：
// Python:  if score >= 60:
//              print("及格")
//          else:
//              print("不及格")

// Java:
int score = 75;
if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}

// 多分支
if (score >= 90) {
    System.out.println("优秀");
} else if (score >= 80) {
    System.out.println("良好");
} else if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}
```

### switch

```java
// Python没有switch，Java有
int day = 3;
switch (day) {
    case 1:
        System.out.println("星期一");
        break;          // ⚠️ 不写break会"穿透"到下一个case！
    case 2:
        System.out.println("星期二");
        break;
    case 3:
        System.out.println("星期三");
        break;
    default:
        System.out.println("其他");
        break;
}
```

### for循环

```java
// Python: for i in range(5):
// Java:
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}

// 增强for循环（类似Python的for-in）
int[] nums = {1, 2, 3, 4, 5};
for (int n : nums) {
    System.out.println(n);
}
```

### while循环

```java
// 和Python一样
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}

// do-while（至少执行一次，Python没有）
do {
    System.out.println(i);
    i++;
} while (i < 5);
```

### break和continue

```java
// break：跳出整个循环
// continue：跳过本次循环，进入下一次
// 和Python完全一样，只是Python用的是break和continue关键字
```

---

## 五、数组

```java
// 声明和初始化
int[] arr1 = new int[5];           // 创建长度为5的数组，默认值都是0
int[] arr2 = {1, 2, 3, 4, 5};     // 直接赋值
int[] arr3 = new int[]{1, 2, 3};   // 另一种写法

// 访问元素（和Python一样用下标）
arr2[0]     // 第一个元素，值为1
arr2.length // 数组长度（注意：Python用len()，Java用.length属性）

// 遍历
for (int i = 0; i < arr2.length; i++) {
    System.out.println(arr2[i]);
}

// 增强for循环遍历
for (int n : arr2) {
    System.out.println(n);
}

// ⚠️ Java数组越界会抛出 ArrayIndexOutOfBoundsException
// Python越界是 IndexError，Java是 ArrayIndexOutOfBoundsException
```

### 二维数组

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// 遍历二维数组
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

---

## 六、方法（函数）

```java
// Python:  def add(a, b):
//              return a + b

// Java:
public static int add(int a, int b) {
    return a + b;
}

// 调用
int result = add(3, 5);    // 结果是8

// 方法签名 = 返回类型 + 方法名 + 参数列表
// public static int add(int a, int b)
//   │       │      │    │         │
// 修饰符  修饰符  返回类型 方法名   参数
```

### 方法重载（Overload）

```java
// 方法名相同，参数不同（类型或个数不同）
public static int add(int a, int b) {
    return a + b;
}

public static double add(double a, double b) {
    return a + b;
}

public static int add(int a, int b, int c) {
    return a + b + c;
}

// 调用时根据参数自动匹配
add(1, 2);          // 调用第一个
add(1.5, 2.5);      // 调用第二个
add(1, 2, 3);       // 调用第三个
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 编译 | compile | 把源代码翻译成字节码 |
| 字节码 | bytecode | JVM能执行的中间代码 |
| 虚拟机 | JVM | Java Virtual Machine，运行Java程序的环境 |
| 强类型 | strongly typed | 变量必须声明类型 |
| 自动类型提升 | automatic promotion | 小类型自动转为大类型 |
| 强制类型转换 | casting | 大类型手动转为小类型 |
| 方法重载 | overloading | 同名方法，参数不同 |
| 数组 | array | 固定长度的同类型数据集合 |

---

> **下一课预告：** 第2课我们将学习Java面向对象编程的核心——类、对象、继承、多态、封装、抽象类和接口。
