package com.learn.spring.data.jpa.repository;

import com.learn.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * 自定义参数jpa接口
 * @author Mr.zxb
 * @date 2019-01-20 22:22:55
 */
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public interface StudentJpaSpecificationExecutor extends JpaRepository<Student, Integer>,
        JpaSpecificationExecutor<Student> {
}
