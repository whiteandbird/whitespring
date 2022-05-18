package com.wang.test.proxytest;

import com.wang.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:05:10  22:55
 */
public class UserDaoMethodInterceptor implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("拦截方法开始干活");
        try{
            System.out.println("拦截了方法"+method.getName()+"参数"+args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
