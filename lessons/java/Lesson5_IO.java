/**
 * 第5课：I/O流与文件操作
 * 包含：字节流、字符流、缓冲流、文件操作、序列化
 *
 * 编译运行：
 *   javac Lesson5_IO.java
 *   java Lesson5_IO
 */

import java.io.*;
import java.util.ArrayList;

public class Lesson5_IO {

    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void printSection(String title) {
        System.out.println("\n" + title);
        System.out.println(repeat("-", 30));
    }

    // ========== Part 1: 字节流 ==========
    public static void byteStreamDemo() {
        printSection("1. 字节流（FileInputStream/FileOutputStream）");

        // --- 写文件 ---
        try (FileOutputStream fos = new FileOutputStream("data/java/byte_output.txt")) {
            String content = "Hello, Java I/O!\n这是第二行\n这是第三行";
            fos.write(content.getBytes("UTF-8"));
            System.out.println("写入文件成功");
        } catch (IOException e) {
            System.out.println("写入失败: " + e.getMessage());
        }

        // --- 读文件 ---
        System.out.println("\n读取文件内容：");
        try (FileInputStream fis = new FileInputStream("data/java/byte_output.txt")) {
            byte[] buffer = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len, "UTF-8"));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }

        // --- 文件复制 ---
        System.out.println("\n文件复制：");
        try {
            copyFile("data/java/byte_output.txt", "data/java/byte_copy.txt");
            System.out.println("复制成功！");
        } catch (IOException e) {
            System.out.println("复制失败: " + e.getMessage());
        }
    }

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

    // ========== Part 2: 字符流 ==========
    public static void charStreamDemo() {
        printSection("2. 字符流（FileReader/FileWriter）");

        // --- 写文本文件 ---
        try (FileWriter fw = new FileWriter("data/java/char_output.txt")) {
            fw.write("你好，Java字符流！\n");
            fw.write("这是第二行，支持中文\n");
            fw.write("第三行内容\n");
            System.out.println("字符流写入成功");
        } catch (IOException e) {
            System.out.println("写入失败: " + e.getMessage());
        }

        // --- 追加写入 ---
        try (FileWriter fw = new FileWriter("data/java/char_output.txt", true)) {
            fw.write("这是追加的内容\n");
            System.out.println("追加写入成功");
        } catch (IOException e) {
            System.out.println("追加失败: " + e.getMessage());
        }

        // --- 读文本文件 ---
        System.out.println("\n字符流读取：");
        try (FileReader fr = new FileReader("data/java/char_output.txt")) {
            char[] buffer = new char[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fr.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }
    }

    // ========== Part 3: 缓冲流 ==========
    public static void bufferedDemo() {
        printSection("3. 缓冲流（BufferedReader/BufferedWriter）");

        // --- BufferedWriter ---
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/java/buffered.txt"))) {
            bw.write("缓冲流写入第一行");
            bw.newLine();
            bw.write("缓冲流写入第二行");
            bw.newLine();
            bw.write("缓冲流写入第三行");
            System.out.println("BufferedWriter写入成功");
        } catch (IOException e) {
            System.out.println("写入失败: " + e.getMessage());
        }

        // --- BufferedReader 按行读取（最常用！）---
        System.out.println("\n按行读取：");
        try (BufferedReader br = new BufferedReader(new FileReader("data/java/buffered.txt"))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("第" + lineNum + "行: " + line);
                lineNum++;
            }
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }

        // --- 读取所有行到List ---
        System.out.println("\n读取所有行到ArrayList：");
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/java/buffered.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        }
        System.out.println("共读取 " + lines.size() + " 行");
        for (String line : lines) {
            System.out.println("  → " + line);
        }
    }

    // ========== Part 4: 文件操作 ==========
    public static void fileDemo() {
        printSection("4. File类文件操作");

        File file = new File("data/java/buffered.txt");

        // 判断
        System.out.println("文件存在: " + file.exists());
        System.out.println("是文件: " + file.isFile());
        System.out.println("是目录: " + file.isDirectory());

        // 获取信息
        System.out.println("文件名: " + file.getName());
        System.out.println("绝对路径: " + file.getAbsolutePath());
        System.out.println("文件大小: " + file.length() + " 字节");

        // 创建目录
        File newDir = new File("data/java/new_folder");
        if (!newDir.exists()) {
            boolean created = newDir.mkdir();
            System.out.println("创建目录: " + created);
        }

        // 创建多级目录
        File multiDir = new File("data/java/a/b/c");
        if (!multiDir.exists()) {
            boolean created = multiDir.mkdirs();
            System.out.println("创建多级目录: " + created);
        }

        // 列出当前目录内容
        System.out.println("\ndata/java/ 目录内容：");
        File dir = new File("data/java");
        if (dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                for (String f : files) {
                    System.out.println("  " + f);
                }
            }
        }
    }

    // ========== Part 5: 序列化 ==========
    // 可序列化的类
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        int age;
        transient double score;  // transient：不参与序列化

        public Student(String name, int age, double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
        }
    }

    public static void serializationDemo() {
        printSection("5. 序列化与反序列化");

        Student s1 = new Student("张三", 20, 95.5);
        System.out.println("序列化前: " + s1);

        // 序列化（对象 → 文件）
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("data/java/student.dat"))) {
            oos.writeObject(s1);
            System.out.println("序列化成功");
        } catch (IOException e) {
            System.out.println("序列化失败: " + e.getMessage());
        }

        // 反序列化（文件 → 对象）
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("data/java/student.dat"))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("反序列化后: " + s2);
            System.out.println("注意：score是transient，反序列化后为默认值0.0");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("反序列化失败: " + e.getMessage());
        }
    }

    // ========== 主方法 ==========
    public static void main(String[] args) {
        System.out.println(repeat("=", 50));
        System.out.println("第5课：I/O流与文件操作");
        System.out.println(repeat("=", 50));

        byteStreamDemo();
        charStreamDemo();
        bufferedDemo();
        fileDemo();
        serializationDemo();

        System.out.println("\n" + repeat("=", 50));
        System.out.println("第5课总结：");
        System.out.println("1. 字节流处理所有文件，字符流只处理文本");
        System.out.println("2. 缓冲流提高效率，BufferedReader按行读取最常用");
        System.out.println("3. try-with-resources自动关闭资源");
        System.out.println("4. File类操作文件和目录");
        System.out.println("5. 序列化：对象↔字节序列，实现Serializable接口");
        System.out.println(repeat("=", 50));
    }
}
