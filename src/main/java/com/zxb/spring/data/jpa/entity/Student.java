package com.zxb.spring.data.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 使用Spring-data-jpa，先开发实体类，自动生成数据表
 * @author Mr.zxb
 * @date 2019-01-20 15:00:23
 */
@Data
@Entity
public class Student {
    @GeneratedValue
    @Id
    private int id;
    @Column(length = 20, nullable = false)
    private String name;
    private int age;
    private Date createDate;

}
