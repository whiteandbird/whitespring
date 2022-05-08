package com.wang.test;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.dao.IUserDao;
import com.wang.test.dao.UserService;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  12:12
 */
public class FactoryBeanTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:factoryBeans.xml");
        context.registerShutHook();
        IUserDao bean = (IUserDao)context.getBean(MyFactoryBean.class);
        System.out.println("quer 结果"+bean.getQuery("wang"));
    }
}
