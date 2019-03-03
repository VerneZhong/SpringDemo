package com.learn.spring.data.jpa.repository;

import com.learn.spring.data.jpa.entity.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * 继承于CrudRepository
 * crudRepository
 * @author Mr.zxb
 * @date 2019-01-20 21:50:00
 */
public interface StudentCrudRepository extends CrudRepository<Student, Integer> {


}
