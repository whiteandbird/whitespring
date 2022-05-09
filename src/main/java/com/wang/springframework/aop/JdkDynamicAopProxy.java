package com.wang.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  15:57
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdvisedSupport support;

    public JdkDynamicAopProxy(AdvisedSupport support){
        this.support = support;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                support.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(support.getMethodMatcher().mathes(method, support.getTargetSource().getClass())){
            // 如果方法匹配了 那么就执行
            MethodInterceptor methodInterceptor = support.getInterceptor();
            // 将原来的方法传了进去
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(support.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(support.getTargetSource().getTarget(), args);
    }
}
