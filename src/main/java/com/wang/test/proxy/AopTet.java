package com.wang.test.proxy;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.dao.IUserService;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:51
 */
public class AopTet {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:methodInteceptor.xml");
        IUserService bean = context.getBean(IUserService.class);
        bean.query("asd");
    }
}
