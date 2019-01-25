package com.zxb.spring.jdbc.mapper;

import com.zxb.spring.jdbc.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 创建表和实体的映射，用于查询返回数据对象
 * @author Mr.zxb
 * @date 2019-01-11 17:10
 */
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setSex(rs.getString("sex"));

        return user;
    }
}
