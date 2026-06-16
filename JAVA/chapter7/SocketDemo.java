package chapter7;

import java.io.*;
import java.net.*;

/**
 * 第7章：网络编程 - TCP Socket编程
 * 期末考试重点：Socket、ServerSocket、TCP通信流程
 *
 * 注意：网络编程需要分别启动服务器和客户端，这里只展示代码结构
 */
public class SocketDemo {
    public static void main(String[] args) {
        System.out.println("=== 第7章：TCP Socket编程 ===");
        System.out.println("（网络编程需要分别启动服务器和客户端，这里只展示代码结构）\n");

        // 打印Socket编程核心知识
        System.out.println("【TCP Socket编程步骤】");
        System.out.println("服务器端：");
        System.out.println("1. 创建ServerSocket，绑定端口");
        System.out.println("2. 调用accept()等待客户端连接");
        System.out.println("3. 获取输入/输出流，进行通信");
        System.out.println("4. 关闭资源");

        System.out.println("\n客户端：");
        System.out.println("1. 创建Socket，指定服务器IP和端口");
        System.out.println("2. 获取输入/输出流，进行通信");
        System.out.println("3. 关闭资源");

        System.out.println("\n【核心类】");
        System.out.println("ServerSocket: 服务器端套接字");
        System.out.println("Socket: 客户端套接字");
        System.out.println("InetAddress: IP地址封装类");

        System.out.println("\n=== 1. TCP服务器端代码 ===");
        System.out.println("public class Server {");
        System.out.println("    public static void main(String[] args) throws IOException {");
        System.out.println("        // 1. 创建服务器Socket，监听8888端口");
        System.out.println("        ServerSocket serverSocket = new ServerSocket(8888);");
        System.out.println("        System.out.println(\"服务器已启动，等待连接...\");");
        System.out.println("");
        System.out.println("        // 2. 等待客户端连接（阻塞）");
        System.out.println("        Socket socket = serverSocket.accept();");
        System.out.println("        System.out.println(\"客户端已连接\");");
        System.out.println("");
        System.out.println("        // 3. 读取客户端消息");
        System.out.println("        InputStream is = socket.getInputStream();");
        System.out.println("        BufferedReader br = new BufferedReader(new InputStreamReader(is));");
        System.out.println("        String message = br.readLine();");
        System.out.println("        System.out.println(\"收到: \" + message);");
        System.out.println("");
        System.out.println("        // 4. 发送响应");
        System.out.println("        OutputStream os = socket.getOutputStream();");
        System.out.println("        PrintWriter pw = new PrintWriter(os, true);");
        System.out.println("        pw.println(\"服务器收到: \" + message);");
        System.out.println("");
        System.out.println("        // 5. 关闭资源");
        System.out.println("        pw.close();");
        System.out.println("        br.close();");
        System.out.println("        socket.close();");
        System.out.println("        serverSocket.close();");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n=== 2. TCP客户端代码 ===");
        System.out.println("public class Client {");
        System.out.println("    public static void main(String[] args) throws IOException {");
        System.out.println("        // 1. 连接服务器");
        System.out.println("        Socket socket = new Socket(\"localhost\", 8888);");
        System.out.println("");
        System.out.println("        // 2. 发送消息");
        System.out.println("        OutputStream os = socket.getOutputStream();");
        System.out.println("        PrintWriter pw = new PrintWriter(os, true);");
        System.out.println("        pw.println(\"你好，服务器！\");");
        System.out.println("");
        System.out.println("        // 3. 读取响应");
        System.out.println("        InputStream is = socket.getInputStream();");
        System.out.println("        BufferedReader br = new BufferedReader(new InputStreamReader(is));");
        System.out.println("        String response = br.readLine();");
        System.out.println("        System.out.println(\"服务器响应: \" + response);");
        System.out.println("");
        System.out.println("        // 4. 关闭资源");
        System.out.println("        br.close();");
        System.out.println("        pw.close();");
        System.out.println("        socket.close();");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n=== 3. 简单聊天室代码 ===");
        System.out.println("// 服务器端");
        System.out.println("public class ChatServer {");
        System.out.println("    public static void main(String[] args) throws IOException {");
        System.out.println("        ServerSocket serverSocket = new ServerSocket(9999);");
        System.out.println("        while (true) {");
        System.out.println("            Socket socket = serverSocket.accept();");
        System.out.println("            new Thread(new ClientHandler(socket)).start();");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("");
        System.out.println("// 客户端处理器");
        System.out.println("class ClientHandler implements Runnable {");
        System.out.println("    private Socket socket;");
        System.out.println("    public ClientHandler(Socket socket) { this.socket = socket; }");
        System.out.println("");
        System.out.println("    @Override");
        System.out.println("    public void run() {");
        System.out.println("        try {");
        System.out.println("            BufferedReader br = new BufferedReader(");
        System.out.println("                new InputStreamReader(socket.getInputStream()));");
        System.out.println("            String message;");
        System.out.println("            while ((message = br.readLine()) != null) {");
        System.out.println("                System.out.println(\"收到: \" + message);");
        System.out.println("            }");
        System.out.println("        } catch (IOException e) {");
        System.out.println("            e.printStackTrace();");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n=== 4. InetAddress类（可运行）===");
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("主机名: " + localhost.getHostName());
            System.out.println("本机IP: " + localhost.getHostAddress());

            InetAddress baidu = InetAddress.getByName("www.baidu.com");
            System.out.println("百度IP: " + baidu.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("获取IP地址失败: " + e.getMessage());
        }

        System.out.println("\n【UDP编程（了解）】");
        System.out.println("UDP使用DatagramSocket和DatagramPacket");
        System.out.println("特点：无需建立连接、不可靠但高效、支持广播");
        System.out.println("应用：视频直播、DNS查询、在线游戏");
    }
}
