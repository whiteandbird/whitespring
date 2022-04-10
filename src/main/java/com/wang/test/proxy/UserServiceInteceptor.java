package com.wang.test.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  16:29
 */
public class UserServiceInteceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("user service 拦截");
        System.out.println("拦截方法名字"+ methodInvocation.getMethod().getName());
        Object proceed = methodInvocation.proceed();
        System.out.println("=fdfsdf");
        System.out.println(proceed);
        return proceed;
    }
}
