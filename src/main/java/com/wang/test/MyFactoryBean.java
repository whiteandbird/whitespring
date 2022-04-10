package com.wang.test;

import com.wang.springframework.beans.factory.support.FactoryBean;
import com.wang.test.dao.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  12:13
 */
public class MyFactoryBean implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            HashMap<String,String> maps = new HashMap<>();
            maps.put("1","wangdana");
            maps.put("2","w");
            return "你被代理了,结果是:"+maps.get(args[0]);
        };
        return (IUserDao) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IUserDao.class}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean singleton() {
        return true;
    }
}
