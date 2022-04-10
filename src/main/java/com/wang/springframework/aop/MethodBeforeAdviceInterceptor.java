package com.wang.springframework.aop;

import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:18
 */
@NoArgsConstructor
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private  MethodBeforeAdvice methodBeforeAdvice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice){
        methodBeforeAdvice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.methodBeforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
