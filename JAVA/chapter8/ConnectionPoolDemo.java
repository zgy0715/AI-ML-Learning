package chapter8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 第8章：数据库编程 - 连接池
 * 期末考试重点：连接池原理、HikariCP、Druid
 *
 * 注意：本文件需要MySQL环境才能运行
 */
public class ConnectionPoolDemo {
    public static void main(String[] args) {
        System.out.println("=== 第8章：连接池 ===");
        System.out.println("（需要MySQL环境，请阅读代码理解连接池原理）\n");

        // 打印连接池核心知识
        System.out.println("【连接池原理】");
        System.out.println("1. 预先创建一定数量的数据库连接");
        System.out.println("2. 使用时从池中获取，用完后归还");
        System.out.println("3. 避免频繁创建和销毁连接，提高性能");
        System.out.println("4. 可以限制最大连接数，防止数据库过载");

        System.out.println("\n【常用连接池】");
        System.out.println("1. HikariCP: 高性能，Spring Boot默认，速度快");
        System.out.println("2. Druid: 阿里巴巴开源，监控功能强大");
        System.out.println("3. C3P0: 老牌连接池，配置灵活");
        System.out.println("4. DBCP: Apache开源，较老");

        System.out.println("\n【HikariCP配置示例】");
        System.out.println("HikariConfig config = new HikariConfig();");
        System.out.println("config.setJdbcUrl(\"jdbc:mysql://localhost:3306/testdb\");");
        System.out.println("config.setUsername(\"root\");");
        System.out.println("config.setPassword(\"password\");");
        System.out.println("config.setMaximumPoolSize(10);      // 最大连接数");
        System.out.println("config.setMinimumIdle(5);           // 最小空闲连接");
        System.out.println("config.setConnectionTimeout(30000);  // 连接超时时间");
        System.out.println("config.setIdleTimeout(600000);       // 空闲超时时间");
        System.out.println("config.setMaxLifetime(1800000);      // 连接最大生命周期");
        System.out.println("");
        System.out.println("HikariDataSource dataSource = new HikariDataSource(config);");
        System.out.println("Connection conn = dataSource.getConnection();");

        System.out.println("\n【JNDI数据源】");
        System.out.println("JNDI: Java Naming and Directory Interface");
        System.out.println("在服务器配置数据源，通过名称获取连接");
        System.out.println("常用于Web应用（Tomcat、WebLogic等）");

        System.out.println("\n【连接池参数说明】");
        System.out.println("maxPoolSize: 最大连接数，默认10");
        System.out.println("minIdle: 最小空闲连接数");
        System.out.println("connectionTimeout: 获取连接超时时间（毫秒）");
        System.out.println("idleTimeout: 空闲连接超时时间（毫秒）");
        System.out.println("maxLifetime: 连接最大生命周期（毫秒）");

        /*
        // 以下代码需要MySQL环境才能运行
        System.out.println("\n=== 简单连接池实现演示 ===");
        SimpleConnectionPool pool = new SimpleConnectionPool(5);
        System.out.println("连接池创建成功，初始连接数: " + pool.getPoolSize());

        try {
            Connection conn1 = pool.getConnection();
            Connection conn2 = pool.getConnection();
            System.out.println("获取2个连接后，可用连接数: " + pool.getAvailableSize());

            pool.releaseConnection(conn1);
            System.out.println("归还1个连接后，可用连接数: " + pool.getAvailableSize());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}

/**
 * 简单连接池实现（教学用，需要MySQL环境）
 */
class SimpleConnectionPool {
    private final BlockingQueue<Connection> pool;
    private final int maxSize;

    public SimpleConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        this.pool = new ArrayBlockingQueue<>(maxSize);
        // 初始化连接
        for (int i = 0; i < maxSize; i++) {
            try {
                pool.offer(DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/testdb",
                        "root", "password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();  // 阻塞等待
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            pool.offer(conn);  // 归还连接
        }
    }

    public int getPoolSize() {
        return pool.size();
    }

    public int getAvailableSize() {
        return pool.size();
    }
}
