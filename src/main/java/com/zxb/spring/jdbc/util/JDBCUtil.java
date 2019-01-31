package com.zxb.spring.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * @author Mr.zxb
 * @date 2019-01-20 10:22:08
 */
public class JDBCUtil {

    /**
     * 获取JDBC connection连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = getProperties();
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        String driverClass = properties.getProperty("driverClass");
        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, pwd);
    }

    /**
     * 获取db配置属性
     * @return
     * @throws IOException
     */
    public static Properties getProperties() throws IOException {
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    /**
     * 释放资源
     * @param resultSet
     * @param statement
     * @param connection
     * @throws SQLException
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
