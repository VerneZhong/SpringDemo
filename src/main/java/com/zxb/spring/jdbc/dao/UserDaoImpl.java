package com.zxb.spring.jdbc.dao;

import com.google.common.collect.Lists;
import com.zxb.spring.jdbc.entity.User;
import com.zxb.spring.jdbc.util.JDBCUtil;

import java.sql.*;
import java.util.List;

/**
 * jdbc操作的示例
 * @author Mr.zxb
 * @date 2019-01-31 09:32
 */
public class UserDaoImpl implements UserDao {

    @Override
    public int save(User user) {
        String sql = "insert into user(name, age, sex) values (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        int row = 0;
        try {
            connection = JDBCUtil.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getSex());

            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.release(null, statement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        List<User> users = Lists.newArrayList();
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setSex(resultSet.getString("sex"));

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.release(null, statement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
