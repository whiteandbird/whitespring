package com.wang.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wangdy
 * @date 2022/5/18 8:58
 */
public class MyMethodBeforeAdviceInteceptor implements MethodInterceptor {
    private MethodBeforeAdvice methodBeforeAdvice;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        methodBeforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        Object result = methodInvocation.proceed();

        return result;
    }
}
