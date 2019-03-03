package com.learn.spring.data.jpa.entity;

import com.learn.spring.data.jpa.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 使用Spring-data-jpa，先开发实体类，自动生成数据表
 * @author Mr.zxb
 * @date 2019-01-20 15:00:23
 */
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, nullable = false)
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(length = 4)
    private Gender gender;
    private Date createDate;

}
