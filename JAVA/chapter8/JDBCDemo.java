package chapter8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 第8章：数据库编程 - JDBC
 * 期末考试重点：JDBC步骤、PreparedStatement、事务、连接池
 *
 * 注意：本文件需要MySQL环境才能运行
 * 1. 安装MySQL 8.0+
 * 2. 创建testdb数据库
 * 3. 修改下方数据库配置（URL、USER、PASSWORD）
 * 4. 添加MySQL驱动到项目（mysql-connector-java-8.0.xx.jar）
 */
public class JDBCDemo {
    // 数据库配置（请根据实际情况修改）
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        System.out.println("=== 第8章：JDBC数据库编程 ===");
        System.out.println("（需要MySQL环境，请阅读代码理解JDBC用法）\n");

        // 以下代码需要MySQL环境才能运行，已注释
        // 如需运行，请先配置MySQL数据库并取消注释

        /*
        System.out.println("=== 1. JDBC连接数据库 ===");

        // 1. 加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败: " + e.getMessage());
            return;
        }

        // 2. 建立连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("连接失败: " + e.getMessage());
            return;
        }

        System.out.println("\n=== 2. Statement执行SQL ===");
        try {
            Statement stmt = conn.createStatement();

            // 创建表
            String createSQL = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(50) NOT NULL,
                        age INT,
                        email VARCHAR(100)
                    )
                    """;
            stmt.executeUpdate(createSQL);
            System.out.println("表创建成功！");

            // 插入数据
            String insertSQL = "INSERT INTO users (name, age, email) VALUES ('张三', 25, 'zhangsan@example.com')";
            int rows = stmt.executeUpdate(insertSQL);
            System.out.println("插入了 " + rows + " 行");

            // 查询数据
            String querySQL = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(querySQL);
            System.out.println("\n查询结果:");
            while (rs.next()) {
                System.out.printf("ID: %d, 姓名: %s, 年龄: %d, 邮箱: %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 3. PreparedStatement（推荐）===");
        // 使用PreparedStatement防止SQL注入
        String insertSQL = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, "李四");
            pstmt.setInt(2, 30);
            pstmt.setString(3, "lisi@example.com");
            int rows = pstmt.executeUpdate();
            System.out.println("PreparedStatement插入了 " + rows + " 行");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 4. 事务处理 ===");
        try {
            conn.setAutoCommit(false);  // 关闭自动提交

            String sql1 = "UPDATE users SET age = age + 1 WHERE name = '张三'";
            String sql2 = "UPDATE users SET age = age - 1 WHERE name = '李四'";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);

            conn.commit();  // 提交事务
            System.out.println("事务提交成功！");
        } catch (SQLException e) {
            try {
                conn.rollback();  // 回滚事务
                System.out.println("事务回滚！");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // 关闭连接
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("\n数据库连接已关闭");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        // 打印JDBC核心步骤（方便记忆）
        System.out.println("【JDBC核心步骤】");
        System.out.println("1. 加载驱动: Class.forName(\"com.mysql.cj.jdbc.Driver\")");
        System.out.println("2. 获取连接: DriverManager.getConnection(url, user, pass)");
        System.out.println("3. 创建Statement: conn.createStatement()");
        System.out.println("4. 执行SQL: stmt.executeQuery(sql) 或 stmt.executeUpdate(sql)");
        System.out.println("5. 处理结果: while (rs.next()) { ... }");
        System.out.println("6. 关闭资源: rs.close(); stmt.close(); conn.close()");

        System.out.println("\n【PreparedStatement防SQL注入】");
        System.out.println("String sql = \"SELECT * FROM users WHERE username = ?\";");
        System.out.println("PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println("pstmt.setString(1, username);  // 参数化查询");
        System.out.println("ResultSet rs = pstmt.executeQuery();");

        System.out.println("\n【事务处理】");
        System.out.println("conn.setAutoCommit(false);  // 关闭自动提交");
        System.out.println("conn.commit();              // 提交事务");
        System.out.println("conn.rollback();            // 回滚事务");
    }
}
