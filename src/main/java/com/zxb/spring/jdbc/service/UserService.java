package com.zxb.spring.jdbc.service;

import com.zxb.spring.jdbc.entity.User;

import java.util.List;

/**
 * 数据操作接口
 * @author Mr.zxb
 * @date 2019-01-11 17:19
 */
public interface UserService {

    void save(User user);

    List<User> getUsers();
}
