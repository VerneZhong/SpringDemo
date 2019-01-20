package com.zxb.spring.jdbc.service;

import com.google.common.collect.Maps;
import com.zxb.spring.jdbc.entity.User;
import com.zxb.spring.jdbc.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.zxb
 * @date 2019-01-11 17:20
 */
@Service
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setParameterJdbcTemplate(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void save(User user) {
        jdbcTemplate.update("insert into user(name, age, sex) values (?, ?, ?)",
                new Object[]{ user.getName(), user.getAge(), user.getSex() },
                new int[]{ Types.VARCHAR, Types.INTEGER, Types.VARCHAR });
    }

    public List<User> getUsers() {
        List<User> list = jdbcTemplate.query("select * from user", new UserRowMapper());
        return list;
    }

    public List<User> queryUserByCondition(User user) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", user.getName());
        List<User> list = parameterJdbcTemplate.query("select * from user where name = :name", map, new UserRowMapper());
        return list;
    }
}
