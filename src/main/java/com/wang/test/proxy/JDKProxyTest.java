package com.wang.test.proxy;

import com.wang.springframework.aop.*;
import com.wang.test.dao.IUserService;
import com.wang.test.dao.UserService;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  16:31
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        IUserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(userService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher(new AspectJExpressPointCut("execution(* com.wang.test.dao.IUserService.*(..))"));
        advisedSupport.setInterceptor(new UserServiceInteceptor());


        IUserService proxy = (IUserService)new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.query("wang");
        System.out.println("=======================");
        userService.query("wamg");
        System.out.println("===================");
        CglibAopProxy cglibAopProxy = new CglibAopProxy(advisedSupport);
        IUserService proxy2 = (IUserService)cglibAopProxy.getProxy();
        proxy2.query("wag");
    }
}
