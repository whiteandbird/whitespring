package com.wang.test.proxytest;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  22:52
 */
public class UserDao implements IProxyUserDao{
    public String query(){
        System.out.println("dao query");
        return "hello world";
    }

    public String query2(String name){
        System.out.println("query  the args is: "+name);
        return name;
    }
}
