package com.wang.test.aroundAop;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.proxytest.UserDao;

/**
 * @author wangdy
 * @date 2022/5/18 14:08
 */
public class AopTestRun {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:aroundAopTest.xml");
        UserDao bean = context.getBean(UserDao.class);
        String s = bean.query2("&&&&&&&&");

        System.out.println(s);
    }
}
