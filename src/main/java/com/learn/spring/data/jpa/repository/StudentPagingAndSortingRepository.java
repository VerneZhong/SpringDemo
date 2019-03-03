package com.learn.spring.data.jpa.repository;

import com.learn.spring.data.jpa.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 带分页的CrudRepository
 * @author Mr.zxb
 * @date 2019-01-20 21:59:14
 */
public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student, Integer> {
}
