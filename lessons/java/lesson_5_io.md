# 第5课：I/O流与文件操作

## 一、I/O流概述

I/O就是**输入（Input）和输出（Output）**，用来读写文件、网络数据等。

```
I/O流的分类：
┌──────────────────────────────────────────────────────┐
│  按方向分：                                             │
│  ├── 输入流（InputStream/Reader）：从文件/网络读数据     │
│  └── 输出流（OutputStream/Writer）：写数据到文件/网络    │
│                                                       │
│  按数据类型分：                                         │
│  ├── 字节流（InputStream/OutputStream）：处理所有文件   │
│  └── 字符流（Reader/Writer）：只处理文本文件             │
│                                                       │
│  按功能分：                                             │
│  ├── 节点流：直接操作数据源                              │
│  └── 处理流（包装流）：增强功能（缓冲、转换等）           │
│                                                       │
│  常用流：                                               │
│  ├── FileInputStream / FileOutputStream（字节流）       │
│  ├── FileReader / FileWriter（字符流）                  │
│  ├── BufferedReader / BufferedWriter（缓冲流）          │
│  └── InputStreamReader / OutputStreamWriter（转换流）   │
└──────────────────────────────────────────────────────┘
```

### 和Python对比

| Java | Python | 用途 |
|------|--------|------|
| FileInputStream | open(file, 'rb') | 读字节 |
| FileOutputStream | open(file, 'wb') | 写字节 |
| FileReader | open(file, 'r') | 读文本 |
| FileWriter | open(file, 'w') | 写文本 |
| BufferedReader | file.readlines() | 按行读 |

---

## 二、字节流

### FileInputStream / FileOutputStream

```java
import java.io.*;

// 写文件（字节流）
FileOutputStream fos = new FileOutputStream("test.txt");
fos.write(72);    // 写入字符 'H' 的ASCII码
fos.write(101);   // 写入 'e'
fos.write(108);   // 写入 'l'
fos.write(108);   // 写入 'l'
fos.write(111);   // 写入 'o'
fos.close();      // ⚠️ 必须关闭！

// 写字节数组
byte[] data = "Hello, World!".getBytes();
FileOutputStream fos2 = new FileOutputStream("test2.txt");
fos2.write(data);
fos2.close();

// 读文件（字节流）
FileInputStream fis = new FileInputStream("test.txt");
int b;
while ((b = fis.read()) != -1) {   // read()返回-1表示结束
    System.out.print((char) b);     // 字节转字符
}
fis.close();

// 读字节数组
FileInputStream fis2 = new FileInputStream("test2.txt");
byte[] buffer = new byte[1024];
int len = fis2.read(buffer);        // 读到buffer中，返回读取的字节数
String content = new String(buffer, 0, len);
System.out.println(content);
fis2.close();
```

### try-with-resources（推荐！自动关闭）

```java
// 不用手动close，自动关闭资源
try (FileOutputStream fos = new FileOutputStream("auto.txt")) {
    fos.write("自动关闭".getBytes());
}   // 自动调用close()

// 多个资源
try (FileInputStream fis = new FileInputStream("in.txt");
     FileOutputStream fos = new FileOutputStream("out.txt")) {
    byte[] buf = new byte[1024];
    int len;
    while ((len = fis.read(buf)) != -1) {
        fos.write(buf, 0, len);
    }
}
```

---

## 三、字符流

字符流专门处理**文本文件**，自动处理编码。

### FileReader / FileWriter

```java
import java.io.*;

// 写文本文件
FileWriter fw = new FileWriter("text.txt");
fw.write("你好，Java！\n");
fw.write("第二行内容\n");
fw.close();

// 读文本文件
FileReader fr = new FileReader("text.txt");
int c;
while ((c = fr.read()) != -1) {     // 读单个字符
    System.out.print((char) c);
}
fr.close();

// 字符数组读取
FileReader fr2 = new FileWriter("text.txt");
char[] buffer = new char[1024];
int len = fr2.read(buffer);
String content = new String(buffer, 0, len);
```

### 追加写入

```java
// FileWriter第二个参数为true，表示追加模式（不覆盖）
FileWriter fw = new FileWriter("log.txt", true);  // 追加
fw.write("新内容\n");
fw.close();
```

---

## 四、缓冲流（处理流）

缓冲流**包装**在节点流外面，提高读写效率。

### BufferedReader / BufferedWriter

```java
import java.io.*;

// BufferedWriter
try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"))) {
    bw.write("第一行");
    bw.newLine();    // 换行（跨平台）
    bw.write("第二行");
    bw.newLine();
    bw.write("第三行");
}

// BufferedReader —— 按行读取（最常用！）
try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {   // readLine()读一行
        System.out.println(line);
    }
}
```

### 按行读取文件（最常用模式）

```java
// 读取文件所有行
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    String line;
    int lineNum = 1;
    while ((line = br.readLine()) != null) {
        System.out.println(lineNum + ": " + line);
        lineNum++;
    }
} catch (FileNotFoundException e) {
    System.out.println("文件不存在");
} catch (IOException e) {
    System.out.println("读取失败");
}
```

---

## 五、文件操作

### File类

```java
import java.io.File;

File file = new File("test.txt");

// 判断
file.exists()           // 文件是否存在
file.isFile()           // 是否是文件
file.isDirectory()      // 是否是目录

// 获取信息
file.getName()          // 文件名
file.getAbsolutePath()  // 绝对路径
file.length()           // 文件大小（字节）
file.lastModified()     // 最后修改时间

// 创建和删除
file.createNewFile()    // 创建文件
file.mkdir()            // 创建目录
file.mkdirs()           // 创建多级目录
file.delete()           // 删除文件

// 列出目录内容
File dir = new File(".");
String[] files = dir.list();       // 文件名数组
File[] files2 = dir.listFiles();   // File对象数组
```

### 文件复制

```java
// 用字节流复制文件
public static void copyFile(String src, String dest) throws IOException {
    try (FileInputStream fis = new FileInputStream(src);
         FileOutputStream fos = new FileOutputStream(dest)) {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
    }
}
```

---

## 六、序列化与反序列化

序列化就是把**对象转换成字节序列**，保存到文件或传输。

```java
import java.io.*;

// 对象必须实现 Serializable 接口
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;  // 版本号
    String name;
    int age;
    transient double score;  // transient：不参与序列化
}

// 序列化（对象 → 文件）
Student s = new Student();
s.name = "张三";
s.age = 20;
s.score = 95.5;

try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("student.dat"))) {
    oos.writeObject(s);     // 写入对象
}

// 反序列化（文件 → 对象）
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("student.dat"))) {
    Student s2 = (Student) ois.readObject();   // 读出对象
    System.out.println(s2.name + ", " + s2.age);
}
```

---

## 术语表

| 中文 | 英文 | 含义 |
|------|------|------|
| 输入流 | InputStream/Reader | 从数据源读取数据 |
| 输出流 | OutputStream/Writer | 向目标写入数据 |
| 字节流 | byte stream | 处理所有类型文件 |
| 字符流 | character stream | 专门处理文本文件 |
| 缓冲流 | buffered stream | 带缓冲区，提高效率 |
| 序列化 | serialization | 对象转字节序列 |
| 反序列化 | deserialization | 字节序列转对象 |
| try-with-resources | try-with-resources | 自动关闭资源 |

---

> **下一课预告：** 第6课我们将学习Java多线程和网络编程——Thread、Runnable、同步、Socket通信。
