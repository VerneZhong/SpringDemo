package com.zxb.test;

import com.zxb.spring.jdbc.entity.User;
import com.zxb.spring.jdbc.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mr.zxb
 * @date 2019-01-11 17:36
 */
public class SpringJDBCTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) context.getBean("userService");

        User user = new User();
        user.setName("zxb");
        user.setAge(25);
        user.setSex("男");

//        userService.save(user);

//        List<User> users = userService.getUsers();
        List<User> users = userService.queryUserByCondition(user);
        System.out.println(users);

    }

    @Test
    public void test2() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> {
            int j;
            for (int i = 2; i <= 100; i++) {
                int k = (int) Math.sqrt(i);
                for (j = 2; j <= k; j++) {
                    if (i % j == 0) {
                        break;
                    }
                }
                if (j > k) {
                    System.out.print(i + " ");
                }
            }
        });

    }

}
