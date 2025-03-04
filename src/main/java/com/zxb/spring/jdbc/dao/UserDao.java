package com.zxb.spring.jdbc.dao;

import com.learn.spring.jdbc.entity.User;

import java.util.List;

/**
 * @author Mr.zxb
 * @date 2019-01-31 09:31
 */
public interface UserDao {

    int save(User user);

    List<User> findAll();
}
