package com.zxb.spring.data.jpa.repository;

import com.zxb.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * RepositoryDefinition注解方式或是继承Repository接口
 * @author Mr.zxb
 * @date 2019-01-20 15:16:31
 */
@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)
public interface StudentRepository { // extends Repository<Student, Integer> {

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    Student findByName(String name);

    /**
     * where name like ?% and age <?
     * 以什么开头 ?%
     * @param name
     * @param age
     * @return
     */
    List<Student> findByNameStartingWithAndAgeLessThan(String name, int age);

    /**
     * where name like %? and age <?
     * 以什么结束
     * @param name
     * @param age
     * @return
     */
    List<Student> findByNameEndingWithAndAgeLessThan(String name, int age);

    /**
     * where name in (?, ?...) or age <?
     * @param names
     * @param age
     * @return
     */
    List<Student> findByNameInOrAgeLessThan(List<String> names, int age);

    /**
     * where name in (?, ?...) and age <?
     * @param names
     * @param age
     * @return
     */
    List<Student> findByNameInAndAgeLessThan(List<String> names, int age);

    /**
     * where createDate between ? and ?
     * @param start
     * @param end
     * @return
     */
    List<Student> findByCreateDateBetween(Date start, Date end);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Student getStudentById(int id);

    /**
     * 注解Query的使用
     * @return
     */
    @Query("select s from Student s where id = (select max(id) from Student)")
    Student queryStudentMaxId();

    /**
     * 注解Query支持索引参数
     * @param name
     * @param age
     * @return
     */
    @Query("select s from Student s where s.name = ?1 and s.age = ?2")
    List<Student> queryStudentByCondition(String name, int age);

    /**
     * 注解Query支持命名参数
     * @param name
     * @param age
     * @return
     */
    @Query("select s from Student s where s.name = :name and s.age = :age")
    List<Student> queryStudentByCondition2(@Param("name") String name, @Param("age") int age);

    /**
     * 注解Query支持like模糊查询
     * @param name
     * @return
     */
//    @Query("select s from Student s where name like %?1%")
    @Query("select s from Student s where name like %:name%")
    List<Student> queryStudentLike(@Param("name") String name);

    /**
     * 原生SQL查询
     * @return
     */
    @Query(value = "select count(1) from student", nativeQuery = true)
    long getCount();

    /**
     * 更新操作
     * @param id
     * @param age
     */
    @Query("update Student set age = ?2 where id = ?1")
    @Modifying
    void updateAgeById(int id, int age);

    /**
     * 删除
     * @param id
     */
    @Modifying
    void deleteById(int id);

    /**
     * 新增
     * @param student
     */
//    @Query("insert into Student(name, age) values(?1, ?2)")
    void save(Student student);
}
