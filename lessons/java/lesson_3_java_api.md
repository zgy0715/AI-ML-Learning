# 第3课：Java常用API

## 一、String字符串

### 创建字符串

```java
// 方式1：直接赋值（推荐）
String s1 = "Hello";

// 方式2：用new（创建了两个对象：堆上的对象 + 常量池中的"Hello"）
String s2 = new String("Hello");

// ⚠️ == 和 equals 的区别（必考！）
System.out.println(s1 == s2);       // false（比较地址）
System.out.println(s1.equals(s2));  // true （比较内容）
```

> 💡 **考试重点：** `==` 比较的是内存地址，`equals()` 比较的是字符串内容。

### 常用方法（必背！）

```java
String s = "Hello, World!";

// 长度
s.length()                    // 13

// 获取某个字符
s.charAt(0)                   // 'H'

// 截取子串
s.substring(0, 5)             // "Hello"（从0开始到5，不包含5）
s.substring(7)                // "World!"（从7到末尾）

// 查找
s.indexOf("World")            // 7（第一次出现的位置）
s.indexOf("o")                // 4（第一次出现的位置）
s.lastIndexOf("o")            // 8（最后一次出现的位置）
s.contains("World")           // true
s.startsWith("Hello")         // true
s.endsWith("!")               // true

// 转换
s.toUpperCase()               // "HELLO, WORLD!"
s.toLowerCase()               // "hello, world!"
s.trim()                      // 去掉两端空格

// 替换
s.replace("World", "Java")    // "Hello, Java!"

// 分割
"a,b,c".split(",")            // 返回数组 ["a", "b", "c"]

// 判断为空
"".isEmpty()                  // true
"  ".isEmpty()                // false（有空格不算空）
```

### StringBuilder（可变字符串）

```java
// String是不可变的，每次修改都创建新对象
// StringBuilder是可变的，适合频繁拼接
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");     // 追加
sb.append("!");
sb.insert(5, ",");       // 在位置5插入
sb.delete(5, 6);         // 删除位置5到6
sb.replace(6, 11, "Java"); // 替换
sb.reverse();            // 反转

String result = sb.toString();  // 转成String
```

---

## 二、包装类（Wrapper Class）

每种基本类型都有对应的包装类：

```
┌──────────┬──────────────┐
│ 基本类型   │ 包装类        │
├──────────┼──────────────┤
│ byte     │ Byte         │
│ short    │ Short        │
│ int      │ Integer      │
│ long     │ Long         │
│ float    │ Float        │
│ double   │ Double       │
│ char     │ Character    │
│ boolean  │ Boolean      │
└──────────┴──────────────┘
```

### 自动装箱和拆箱

```java
// 自动装箱：基本类型 → 包装类（自动）
Integer num1 = 100;          // 等价于 Integer.valueOf(100)

// 自动拆箱：包装类 → 基本类型（自动）
int num2 = num1;             // 等价于 num1.intValue()

// 常用转换
String str = "123";
int n = Integer.parseInt(str);     // 字符串 → int
double d = Double.parseDouble("3.14"); // 字符串 → double

String s1 = String.valueOf(100);   // int → 字符串
String s2 = Integer.toString(100); // int → 字符串

// 常量
int max = Integer.MAX_VALUE;       // 2147483647
int min = Integer.MIN_VALUE;       // -2147483648
```

---

## 三、Math数学工具类

```java
// 全是静态方法，直接用 Math.调用
Math.abs(-10)              // 10（绝对值）
Math.max(10, 20)           // 20（最大值）
Math.min(10, 20)           // 10（最小值）
Math.pow(2, 10)            // 1024.0（2的10次方）
Math.sqrt(16)              // 4.0（平方根）
Math.random()              // 0.0到1.0之间的随机数
Math.round(3.6)            // 4（四舍五入）
Math.ceil(3.2)             // 4.0（向上取整）
Math.floor(3.8)            // 3.0（向下取整）

// 生成指定范围的随机整数
// 生成 [min, max] 之间的随机数
int min = 1, max = 100;
int random = (int) (Math.random() * (max - min + 1)) + min;
```

---

## 四、异常处理

### 什么是异常？

```
程序运行时发生的错误

常见异常：
├── 算术异常：ArithmeticException（如 10/0）
├── 数组越界：ArrayIndexOutOfBoundsException
├── 空指针：NullPointerException
├── 类型转换：ClassCastException
├── 数字格式：NumberFormatException
└── 输入异常：InputMismatchException
```

### try-catch-finally

```java
try {
    // 可能出错的代码
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // 出错时执行
    System.out.println("算术错误：" + e.getMessage());
} finally {
    // 无论如何都会执行（通常用来释放资源）
    System.out.println("finally执行了");
}
```

### 多重catch

```java
try {
    int[] arr = {1, 2, 3};
    System.out.println(arr[5]);     // 越界
    int result = 10 / 0;            // 算术异常
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("数组越界");
} catch (ArithmeticException e) {
    System.out.println("算术异常");
} catch (Exception e) {              // 兜底，捕获所有异常
    System.out.println("其他异常：" + e.getMessage());
}
```

### throws和throw

```java
// throws：声明方法可能抛出的异常（让调用者处理）
public static int divide(int a, int b) throws ArithmeticException {
    if (b == 0) {
        throw new ArithmeticException("除数不能为0");  // throw：手动抛出异常
    }
    return a / b;
}

// 调用时必须处理
try {
    int result = divide(10, 0);
} catch (ArithmeticException e) {
    System.out.println(e.getMessage());  // 除数不能为0
}
```

### 异常处理流程

```
┌─────────────────────────────────────────────┐
│  try {                                       │
│      可能出错的代码                            │
│  } catch (异常类型 变量名) {                    │
│      处理异常                                 │
│  } finally {                                 │
│      无论如何都执行（可选）                     │
│  }                                           │
│                                              │
│  执行流程：                                   │
│  1. 执行try中的代码                           │
│  2. 如果出错 → 跳到对应的catch                 │
│  3. 执行catch中的处理逻辑                     │
│  4. 无论如何 → 执行finally                    │
│  5. 继续执行后续代码                           │
└─────────────────────────────────────────────┘
```

---

## 五、日期和时间（了解）

```java
// Java 8之前（了解即可）
import java.util.Date;
import java.text.SimpleDateFormat;

Date now = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String formatted = sdf.format(now);
System.out.println(formatted);  // 2026-06-16 14:30:00

// Java 8之后（推荐）
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

LocalDate today = LocalDate.now();           // 当前日期
LocalDateTime now2 = LocalDateTime.now();    // 当前日期时间

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String str = now2.format(formatter);
System.out.println(str);
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 字符串 | String | 不可变的字符序列 |
| 可变字符串 | StringBuilder | 可变的字符序列，适合频繁拼接 |
| 包装类 | Wrapper Class | 基本类型的对象形式 |
| 自动装箱 | autoboxing | 基本类型自动转为包装类 |
| 自动拆箱 | unboxing | 包装类自动转为基本类型 |
| 异常 | exception | 程序运行时的错误 |
| 捕获异常 | catch | 处理异常 |
| 抛出异常 | throw/throws | 声明或手动抛出异常 |
| finally | finally | 无论如何都执行的代码块 |

---

> **下一课预告：** 第4课我们将学习Java集合框架——List、Set、Map的使用，以及泛型编程。
