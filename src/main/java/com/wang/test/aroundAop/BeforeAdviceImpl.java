package com.wang.test.aroundAop;

import com.wang.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wangdy
 * @date 2022/5/18 13:54
 */
public class BeforeAdviceImpl implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("before advice"+" 订单前置拦截");
        System.out.println("method: "+method);
        System.out.println("args: "+ Arrays.stream(args).peek(System.out::println).count());
        System.out.println("target: "+target);
        System.out.println("=======================================");
    }
}
