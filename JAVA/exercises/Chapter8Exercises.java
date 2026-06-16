package exercises;

import java.sql.*;

/**
 * 第8章练习题：数据库编程
 * 包含：改错题、填空题、设计题
 */
public class Chapter8Exercises {

    public static void main(String[] args) {
        System.out.println("========== 第8章练习题 ==========\n");

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

        // 错误1：SQL注入风险
        System.out.println("错误1：SQL注入风险");
        System.out.println("原代码：");
        System.out.println("  String name = \"admin' --\";");
        System.out.println("  String sql = \"SELECT * FROM users WHERE username = '\" + name + \"'\";");
        System.out.println("分析：字符串拼接导致SQL注入漏洞");
        System.out.println("修正：使用PreparedStatement\n");

        // 错误2：资源未关闭
        System.out.println("错误2：资源未关闭");
        System.out.println("原代码：");
        System.out.println("  Connection conn = DriverManager.getConnection(url, user, pass);");
        System.out.println("  Statement stmt = conn.createStatement();");
        System.out.println("  ResultSet rs = stmt.executeQuery(sql);");
        System.out.println("  // 使用后未关闭资源");
        System.out.println("分析：数据库资源必须关闭");
        System.out.println("修正：使用try-with-resources\n");

        // 错误3：事务未提交
        System.out.println("错误3：事务未提交");
        System.out.println("原代码：");
        System.out.println("  conn.setAutoCommit(false);");
        System.out.println("  stmt.executeUpdate(sql1);");
        System.out.println("  stmt.executeUpdate(sql2);");
        System.out.println("  // 未提交事务");
        System.out.println("分析：关闭自动提交后必须手动提交");
        System.out.println("修正：添加conn.commit()\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        System.out.println("SQL注入防护示例：");
        String name = "admin' --";
        String sql = "SELECT * FROM users WHERE username = ?";
        System.out.println("原危险SQL: SELECT * FROM users WHERE username = '" + name + "'");
        System.out.println("安全SQL: " + sql);
        System.out.println("参数: " + name);
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：加载驱动
        System.out.println("填空1：加载驱动");
        System.out.println("代码：");
        System.out.println("  Class.______(\"com.mysql.cj.jdbc.Driver\");  // 第一空");
        System.out.println("答案：第一空填 forName\n");

        // 填空2：获取连接
        System.out.println("填空2：获取连接");
        System.out.println("代码：");
        System.out.println("  Connection conn = ______.getConnection(url, user, pass);  // 第二空");
        System.out.println("答案：第二空填 DriverManager\n");

        // 填空3：执行查询
        System.out.println("填空3：执行查询");
        System.out.println("代码：");
        System.out.println("  Statement stmt = conn.createStatement();");
        System.out.println("  ResultSet rs = stmt.______(sql);  // 第三空");
        System.out.println("答案：第三空填 executeQuery\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        System.out.println("JDBC基本语法：");
        System.out.println("1. Class.forName(driver);");
        System.out.println("2. Connection conn = DriverManager.getConnection(url, user, pass);");
        System.out.println("3. Statement stmt = conn.createStatement();");
        System.out.println("4. ResultSet rs = stmt.executeQuery(sql);");
        System.out.println("5. while (rs.next()) { ... }");
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个用户管理模块");
        System.out.println("要求：");
        System.out.println("1. 使用JDBC操作MySQL数据库");
        System.out.println("2. 实现增删改查功能");
        System.out.println("3. 使用PreparedStatement防止SQL注入");
        System.out.println("4. 使用事务保证数据一致性\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("public class UserDao {");
        System.out.println("    private static final String URL = \"jdbc:mysql://localhost:3306/testdb\";");
        System.out.println("    private static final String USER = \"root\";");
        System.out.println("    private static final String PASS = \"password\";");
        System.out.println("");
        System.out.println("    private Connection getConnection() throws SQLException {");
        System.out.println("        return DriverManager.getConnection(URL, USER, PASS);");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    public User findById(int id) {");
        System.out.println("        String sql = \"SELECT * FROM users WHERE id = ?\";");
        System.out.println("        try (Connection conn = getConnection();");
        System.out.println("             PreparedStatement pstmt = conn.prepareStatement(sql)) {");
        System.out.println("            pstmt.setInt(1, id);");
        System.out.println("            ResultSet rs = pstmt.executeQuery();");
        System.out.println("            if (rs.next()) {");
        System.out.println("                return new User(rs.getInt(\"id\"), rs.getString(\"name\"), rs.getInt(\"age\"));");
        System.out.println("            }");
        System.out.println("        } catch (SQLException e) { e.printStackTrace(); }");
        System.out.println("        return null;");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    public boolean insert(User user) {");
        System.out.println("        String sql = \"INSERT INTO users (name, age) VALUES (?, ?)\";");
        System.out.println("        try (Connection conn = getConnection();");
        System.out.println("             PreparedStatement pstmt = conn.prepareStatement(sql)) {");
        System.out.println("            pstmt.setString(1, user.getName());");
        System.out.println("            pstmt.setInt(2, user.getAge());");
        System.out.println("            return pstmt.executeUpdate() > 0;");
        System.out.println("        } catch (SQLException e) { e.printStackTrace(); }");
        System.out.println("        return false;");
        System.out.println("    }");
        System.out.println("");
        System.out.println("    public boolean transfer(int fromId, int toId, int amount) {");
        System.out.println("        Connection conn = null;");
        System.out.println("        try {");
        System.out.println("            conn = getConnection();");
        System.out.println("            conn.setAutoCommit(false);");
        System.out.println("            String sql1 = \"UPDATE users SET balance = balance - ? WHERE id = ?\";");
        System.out.println("            PreparedStatement pstmt1 = conn.prepareStatement(sql1);");
        System.out.println("            pstmt1.setInt(1, amount);");
        System.out.println("            pstmt1.setInt(2, fromId);");
        System.out.println("            pstmt1.executeUpdate();");
        System.out.println("            String sql2 = \"UPDATE users SET balance = balance + ? WHERE id = ?\";");
        System.out.println("            PreparedStatement pstmt2 = conn.prepareStatement(sql2);");
        System.out.println("            pstmt2.setInt(1, amount);");
        System.out.println("            pstmt2.setInt(2, toId);");
        System.out.println("            pstmt2.executeUpdate();");
        System.out.println("            conn.commit();");
        System.out.println("            return true;");
        System.out.println("        } catch (SQLException e) {");
        System.out.println("            if (conn != null) { try { conn.rollback(); } catch (SQLException ex) {} }");
        System.out.println("            return false;");
        System.out.println("        } finally {");
        System.out.println("            if (conn != null) { try { conn.close(); } catch (SQLException e) {} }");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n【运行结果】");
        System.out.println("用户管理模块设计完成，包含基本CRUD操作和事务处理");
    }
}
