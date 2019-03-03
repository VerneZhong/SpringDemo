package com.learn.spring.data.jpa.service;

import com.learn.spring.data.jpa.entity.Student;
import com.learn.spring.data.jpa.repository.StudentCrudRepository;
import com.learn.spring.data.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.zxb
 * @date 2019-01-20 21:25:31
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCrudRepository studentCrudRepository;

    /**
     * 事务一般是声明在Service层，Service会有多个Dao调用，保证多个Dao的操作在一个事务中
     * @param id
     * @param age
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAgeById(int id, int age) {
        studentRepository.updateAgeById(id, age);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<Student> students) {
        studentCrudRepository.save(students);
    }

}
