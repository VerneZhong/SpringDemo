package com.zxb.spring.jdbc.service;

import com.google.common.collect.Maps;
import com.zxb.spring.jdbc.entity.User;
import com.zxb.spring.jdbc.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring jdbc使用案例
 * @author Mr.zxb
 * @date 2019-01-11 17:20
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 索引参数jdbc模板
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 命名参数jdbc模板
     */
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    /**
     * 简单的jdbc insert模板
     */
    private SimpleJdbcInsert simpleJdbcInsert;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setParameterJdbcTemplate(DataSource dataSource) {
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setSimpleJdbcInsert(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    /**
     * jdbcTemplate 添加一个用户
     * @param user
     */
    public void save(User user) {
        jdbcTemplate.update("insert into user(name, age, sex) values (?, ?, ?)",
                new Object[]{ user.getName(), user.getAge(), user.getSex() },
                new int[]{ Types.VARCHAR, Types.INTEGER, Types.VARCHAR });
    }

    /**
     * jdbcTemplate 查询所有用户
     * @return
     */
    public List<User> getUsers() {
        List<User> list = jdbcTemplate.query("select * from user", new UserRowMapper());
        return list;
    }

    /**
     * jdbcTemplate 索引参数查询用户
     * @param age
     * @return
     */
    public List<User> queryUserByAge(int age) {
        List<User> users = jdbcTemplate.query("select * from user where age = ?",
                new Object[]{ age },
                new int[]{ Types.INTEGER },
                new UserRowMapper());
        return users;
    }

    /**
     * namedParameterJdbcTemplate 命名参数绑定查询用户
     * @param user
     * @return
     */
    @Override
    public List<User> queryUserByCondition(User user) {
        // map参数结构绑定
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", user.getName());
        // 表和实体间的映射
        RowMapper<User> rowMapper = new UserRowMapper();
        List<User> list = parameterJdbcTemplate.query("select * from user where name = :name", parameterSource, rowMapper);
        return list;
    }

    public void addUser(User user) {
        // 支持对象和参数映射，自动将对象映射为参数结构
        SqlParameterSource source = new BeanPropertySqlParameterSource(user);
        parameterJdbcTemplate.update("insert into user(name, age, sex) values (:name, :age, :sex)", source);
    }

    /**
     * 命名参数绑定namedParameterJdbcTemplate 保存用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("name", user.getName());
        param.put("age", user.getAge());
        param.put("sex", user.getSex());

        int rows = parameterJdbcTemplate.update("insert into user(name, age, sex) values (:name, :age, :sex)", param);
        return rows;
    }

    /**
     * simpleJdbcInsert 新增用户,并返回主键
     * @param user
     * @return
     */
    @Override
    public long insert(User user) {
        // 绑定表和声明主键列
        simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        // 设置参数
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("name", user.getName());
        parameters.put("age", user.getAge());
        parameters.put("sex", user.getSex());
        // 执行插入传入参数，普通insert，返回影响行数
//        simpleJdbcInsert.execute(parameters);
        // 返回主键，有自增id的单条insert
        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        return id.intValue();
    }

}
