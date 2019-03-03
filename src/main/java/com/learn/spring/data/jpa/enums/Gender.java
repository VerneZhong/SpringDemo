package com.learn.spring.data.jpa.enums;

/**
 * @author Mr.zxb
 * @date 2019-01-27 14:21:54
 */
public enum Gender {

    MAIL("男性"), FMAIL("女性");

    String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
