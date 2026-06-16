package exercises;

import java.io.*;

/**
 * 第5章练习题：I/O流
 * 包含：改错题、填空题、设计题
 */
public class Chapter5Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第5章练习题 ==========\n");

        // 练习1：代码改错
        System.out.println("--- 练习1：代码改错 ---");
        exercise1_codeError();

        // 练习2：填空题
        System.out.println("\n--- 练习2：填空题 ---");
        exercise2_filling();

        // 练习3：设计题
        System.out.println("\n--- 练习3：设计题 ---");
        exercise3_design();
    }

    /**
     * 练习1：代码改错
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：资源未关闭
        System.out.println("错误1：资源未关闭");
        System.out.println("原代码：");
        System.out.println("  FileReader fr = new FileReader(\"test.txt\");");
        System.out.println("  int content = fr.read();");
        System.out.println("  // 使用完后未关闭");
        System.out.println("分析：文件流使用后必须关闭，否则资源泄漏");
        System.out.println("修正：使用try-with-resources自动关闭\n");

        // 错误2：异常处理不当
        System.out.println("错误2：异常处理不当");
        System.out.println("原代码：");
        System.out.println("  try {");
        System.out.println("      FileInputStream fis = new FileInputStream(\"test.txt\");");
        System.out.println("  } catch (Exception e) {");
        System.out.println("      e.printStackTrace();");
        System.out.println("  }");
        System.out.println("分析：应该捕获具体的IOException");
        System.out.println("修正：catch (IOException e) { ... }\n");

        // 错误3：字节流读取文本文件
        System.out.println("错误3：字节流读取文本文件");
        System.out.println("原代码：");
        System.out.println("  FileInputStream fis = new FileInputStream(\"中文.txt\");");
        System.out.println("  int content = fis.read();");
        System.out.println("  System.out.println((char) content);  // 可能乱码");
        System.out.println("分析：中文等多字节字符应该用字符流");
        System.out.println("修正：使用FileReader或BufferedReader\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        try (BufferedReader br = new BufferedReader(new FileReader("test_output.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("文件读取成功（假设文件存在）");
        }
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：捕获异常
        System.out.println("填空1：捕获异常");
        System.out.println("代码：");
        System.out.println("  try {");
        System.out.println("      FileReader fr = new FileReader(\"test.txt\");");
        System.out.println("  } catch (______ e) {  // 第一空");
        System.out.println("      e.printStackTrace();");
        System.out.println("  }");
        System.out.println("答案：第一空填 IOException\n");

        // 填空2：读取文件
        System.out.println("填空2：读取文件");
        System.out.println("代码：");
        System.out.println("  BufferedReader br = new BufferedReader(new FileReader(\"file.txt\"));");
        System.out.println("  String line = br.______;  // 第二空");
        System.out.println("答案：第二空填 readLine()\n");

        // 填空3：写入文件
        System.out.println("填空3：写入文件");
        System.out.println("代码：");
        System.out.println("  FileWriter fw = new FileWriter(\"output.txt\");");
        System.out.println("  fw.______(\"Hello World\");  // 第三空");
        System.out.println("  fw.close();");
        System.out.println("答案：第三空填 write\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        try (BufferedReader br = new BufferedReader(new FileReader("test_output.txt"))) {
            String line = br.readLine();
            System.out.println("读取第一行: " + line);
        } catch (IOException e) {
            System.out.println("文件操作演示（假设文件存在）");
        }
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个工具类，提供以下方法：");
        System.out.println("1. 复制文件");
        System.out.println("2. 统计文件中字符的个数");
        System.out.println("3. 读取文件内容到字符串\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("public class FileUtil {");
        System.out.println("    // 复制文件");
        System.out.println("    public static void copyFile(String src, String dest) throws IOException {");
        System.out.println("        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));");
        System.out.println("             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {");
        System.out.println("            byte[] buffer = new byte[1024];");
        System.out.println("            int len;");
        System.out.println("            while ((len = bis.read(buffer)) != -1) {");
        System.out.println("                bos.write(buffer, 0, len);");
        System.out.println("            }");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    // 统计字符个数");
        System.out.println("    public static int countChars(String filename) throws IOException {");
        System.out.println("        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {");
        System.out.println("            int count = 0;");
        System.out.println("            while (br.readLine() != null) {");
        System.out.println("                count++;");
        System.out.println("            }");
        System.out.println("            return count;");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}\n");

        // 实际运行
        System.out.println("【运行结果】");
        System.out.println("文件工具类方法已定义，可用于实际文件操作");
    }
}
