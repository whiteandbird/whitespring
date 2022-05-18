package com.wang.test.aroundAop;

import com.wang.springframework.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

/**
 * @author wangdy
 * @date 2022/5/18 13:56
 */
public class AfterAdviceImpl implements MethodAfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) {
        System.out.println("after advice"+" 订单后拦截");
        System.out.println("method: "+method);
        System.out.println("args: "+args);
        System.out.println("target: "+target);    }
}
