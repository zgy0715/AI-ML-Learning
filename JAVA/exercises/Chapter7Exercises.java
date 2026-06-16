package exercises;

import java.net.*;

/**
 * 第7章练习题：网络编程
 * 包含：改错题、填空题、设计题
 */
public class Chapter7Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第7章练习题 ==========\n");

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

        // 错误1：端口号超出范围
        System.out.println("错误1：端口号超出范围");
        System.out.println("原代码：");
        System.out.println("  ServerSocket serverSocket = new ServerSocket(70000);");
        System.out.println("分析：端口号范围是0-65535");
        System.out.println("修正：ServerSocket serverSocket = new ServerSocket(8888);\n");

        // 错误2：未关闭资源
        System.out.println("错误2：未关闭资源");
        System.out.println("原代码：");
        System.out.println("  Socket socket = new Socket(\"localhost\", 8888);");
        System.out.println("  // 使用socket后未关闭");
        System.out.println("分析：网络资源使用后必须关闭");
        System.out.println("修正：使用try-with-resources或在finally中关闭\n");

        // 错误3：连接超时未处理
        System.out.println("错误3：连接超时未处理");
        System.out.println("原代码：");
        System.out.println("  Socket socket = new Socket(\"192.168.1.100\", 8888);");
        System.out.println("分析：可能抛出ConnectException，需要处理");
        System.out.println("修正：try { ... } catch (ConnectException e) { ... }\n");

        // InetAddress示例（无需Socket即可运行）
        System.out.println("【InetAddress示例】");
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("本机地址: " + localhost.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("获取IP失败（不影响学习）");
        }
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：创建服务器端Socket
        System.out.println("填空1：创建服务器端Socket");
        System.out.println("代码：");
        System.out.println("  ______ serverSocket = new ______(8080);  // 第一空，第二空");
        System.out.println("答案：第一空填 ServerSocket，第二空填 ServerSocket\n");

        // 填空2：客户端连接服务器
        System.out.println("填空2：客户端连接服务器");
        System.out.println("代码：");
        System.out.println("  Socket socket = new Socket(______, 8080);  // 第三空");
        System.out.println("答案：第三空填 \"localhost\" 或 \"127.0.0.1\"\n");

        // 填空3：获取输入流
        System.out.println("填空3：获取输入流");
        System.out.println("代码：");
        System.out.println("  Socket socket = new Socket(\"localhost\", 8080);");
        System.out.println("  InputStream is = socket.______();  // 第四空");
        System.out.println("答案：第四空填 getInputStream\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        try {
            // 创建服务器端Socket（不实际监听）
            System.out.println("服务器端Socket创建语法正确");

            // 获取本机地址
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("localhost地址: " + addr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个简单的文件传输程序");
        System.out.println("要求：");
        System.out.println("1. 客户端发送文件名给服务器");
        System.out.println("2. 服务器读取文件并发送给客户端");
        System.out.println("3. 客户端保存收到的文件\n");

        System.out.println("【参考答案】");
        System.out.println("服务器端代码：");
        System.out.println("public class FileServer {");
        System.out.println("    public static void main(String[] args) throws IOException {");
        System.out.println("        ServerSocket serverSocket = new ServerSocket(9999);");
        System.out.println("        System.out.println(\"文件服务器启动...\");");
        System.out.println("        while (true) {");
        System.out.println("            Socket socket = serverSocket.accept();");
        System.out.println("            new Thread(() -> handleClient(socket)).start();");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("    private static void handleClient(Socket socket) {");
        System.out.println("        try {");
        System.out.println("            BufferedReader br = new BufferedReader(");
        System.out.println("                new InputStreamReader(socket.getInputStream()));");
        System.out.println("            String fileName = br.readLine();");
        System.out.println("            File file = new File(fileName);");
        System.out.println("            if (file.exists()) {");
        System.out.println("                FileInputStream fis = new FileInputStream(file);");
        System.out.println("                OutputStream os = socket.getOutputStream();");
        System.out.println("                byte[] buffer = new byte[1024];");
        System.out.println("                int len;");
        System.out.println("                while ((len = fis.read(buffer)) != -1) {");
        System.out.println("                    os.write(buffer, 0, len);");
        System.out.println("                }");
        System.out.println("                fis.close();");
        System.out.println("            }");
        System.out.println("            socket.close();");
        System.out.println("        } catch (IOException e) { e.printStackTrace(); }");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n客户端代码：");
        System.out.println("public class FileClient {");
        System.out.println("    public static void main(String[] args) throws IOException {");
        System.out.println("        Socket socket = new Socket(\"localhost\", 9999);");
        System.out.println("        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);");
        System.out.println("        pw.println(\"test.txt\");");
        System.out.println("        InputStream is = socket.getInputStream();");
        System.out.println("        FileOutputStream fos = new FileOutputStream(\"received_test.txt\");");
        System.out.println("        byte[] buffer = new byte[1024];");
        System.out.println("        int len;");
        System.out.println("        while ((len = is.read(buffer)) != -1) {");
        System.out.println("            fos.write(buffer, 0, len);");
        System.out.println("        }");
        System.out.println("        fos.close();");
        System.out.println("        socket.close();");
        System.out.println("        System.out.println(\"文件接收完成！\");");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n【运行结果】");
        System.out.println("文件传输程序设计完成，需要启动服务器和客户端分别运行");
    }
}
