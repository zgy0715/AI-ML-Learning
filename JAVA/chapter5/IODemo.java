package chapter5;

import java.io.*;

/**
 * 第5章：I/O流 - 字节流与字符流
 * 期末考试重点：FileInputStream/OutputStream、FileReader/Writer、缓冲流
 */
public class IODemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 字节流（处理所有文件）===");

        // 1.1 FileOutputStream写入文件
        try (FileOutputStream fos = new FileOutputStream("test_output.txt")) {
            String content = "Hello, Java I/O!\n这是第二行\n这是第三行";
            fos.write(content.getBytes());
            System.out.println("文件写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.2 FileInputStream读取文件
        try (FileInputStream fis = new FileInputStream("test_output.txt")) {
            int content;
            StringBuilder sb = new StringBuilder();
            while ((content = fis.read()) != -1) {
                sb.append((char) content);
            }
            System.out.println("文件内容:\n" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. 缓冲流（提高效率）===");

        // 2.1 BufferedOutputStream写入
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("buffered_output.txt"))) {
            String content = "缓冲流写入的内容\n效率更高！";
            bos.write(content.getBytes());
            System.out.println("缓冲流写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.2 BufferedReader读取
        try (BufferedReader br = new BufferedReader(new FileReader("buffered_output.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println("缓冲流读取:\n" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 3. 字符流（处理文本文件）===");

        // 3.1 FileWriter写入
        try (FileWriter fw = new FileWriter("char_output.txt")) {
            fw.write("字符流写入\n支持中文\n");
            System.out.println("字符流写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.2 FileReader读取
        try (FileReader fr = new FileReader("char_output.txt")) {
            char[] buffer = new char[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fr.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            System.out.println("字符流读取:\n" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 4. 文件复制 ===");
        copyFile("test_output.txt", "test_copy.txt");
        System.out.println("文件复制完成！");

        System.out.println("\n=== 5. 文件操作 ===");
        File file = new File("test_output.txt");
        System.out.println("文件名: " + file.getName());
        System.out.println("绝对路径: " + file.getAbsolutePath());
        System.out.println("是否存在: " + file.exists());
        System.out.println("文件大小: " + file.length() + " bytes");
        System.out.println("是否可读: " + file.canRead());
        System.out.println("是否可写: " + file.canWrite());
    }

    /**
     * 使用缓冲流复制文件
     */
    public static void copyFile(String src, String dest) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
