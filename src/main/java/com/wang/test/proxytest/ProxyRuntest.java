package com.wang.test.proxytest;

import com.wang.springframework.context.support.ClassPathXmlApplicationContext;
import com.wang.test.dao.IUserService;
import com.wang.test.dao.UserService;
import com.wang.test.event.CustomEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  23:01
 */
public class ProxyRuntest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:proxytest.xml");
        ProxyTestService bean = context.getBean(ProxyTestService.class);
        bean.serviceQuery();
        context.close();

    }
}
