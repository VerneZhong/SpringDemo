package com.zxb.spring.data.jpa.repository;

import com.zxb.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Mr.zxb
 * @date 2019-01-20 22:22:55
 */
public interface StudentJpaSpecificationExecutor extends JpaRepository<Student, Integer>,
        JpaSpecificationExecutor<Student> {
}
