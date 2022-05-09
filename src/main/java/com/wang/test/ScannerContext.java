package com.wang.test;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.dao.CoponentDao;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:09  21:55
 */
public class ScannerContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:scan.xml");
        CoponentDao wang = (CoponentDao) context.getBean("wang");
        wang.say();
    }
}
