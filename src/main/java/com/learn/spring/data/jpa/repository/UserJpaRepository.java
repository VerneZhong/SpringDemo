package com.learn.spring.data.jpa.repository;

import com.learn.spring.jdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.zxb
 * @date 2019-01-25 16:23
 */
public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
