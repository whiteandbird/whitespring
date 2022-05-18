package com.wang.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wangdy
 * @date 2022/5/18 9:52
 */
public class AroundMethodInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice before;

    private MethodAfterAdvice after;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if(null != before){
            before.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        }
        Object proceed = methodInvocation.proceed();
        if(null != after){
            after.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        }
        return proceed;
    }
}
