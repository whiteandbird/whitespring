package com.wang.test.proxytest;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  22:51
 */
public class ProxyTestService {
    private IProxyUserDao userDao;


    public void serviceQuery(){
        String query = userDao.query();
        System.out.println("the userdao result is "+query);
    }
}
