package com.wang.test;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.dao.UserService;
import com.wang.test.event.CustomEvent;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  14:43
 */
public class ContextCloseTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beanClose.xml");
        context.publishEvent(new CustomEvent(context,1));
        context.registerShutHook();

    }
}
