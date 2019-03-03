package com.learn.spring.data.jpa.repository;

import com.learn.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpaRepository
 * @author Mr.zxb
 * @date 2019-01-20 22:15:51
 */
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
}
