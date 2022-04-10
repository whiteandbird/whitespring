package com.wang.test.proxy;

import com.wang.test.dao.IUserDao;

import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  15:04
 */
public class ProxyTest {
    public static void main(String[] args) {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class},
                ((proxy, method, args1) -> "你被代z理了"));
        String query = userDao.getQuery("1");
        System.out.println(query);
    }
}
