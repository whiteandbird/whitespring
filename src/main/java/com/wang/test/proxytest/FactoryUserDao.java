package com.wang.test.proxytest;

import com.wang.springframework.beans.factory.support.FactoryBean;
import com.wang.test.dao.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  23:25
 */
public class FactoryUserDao implements FactoryBean<IProxyUserDao> {
    @Override
    public IProxyUserDao getObject() {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            HashMap<String,String> maps = new HashMap<>();
            maps.put("1","wangdana");
            maps.put("2","w");
            return "你被代理了,结果是:";
        };
        return (IProxyUserDao) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IProxyUserDao.class}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return IProxyUserDao.class;
    }

    @Override
    public boolean singleton() {
        return true;
    }
}
