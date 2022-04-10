package com.wang.test;

import com.wang.springframework.BeanFactory;
import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.entity.Person;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  14:57
 */
public class ClassXmlTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        context.registerShutHook();
        Person person = context.getBean("person", Person.class);
        System.out.println(person);

        Person person1 = context.getBean("person", Person.class);
        System.out.println(person1);


    }
}
