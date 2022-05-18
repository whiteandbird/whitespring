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
        return Proxy.newProxyInstance(support.getTargetSource().getTarget().getClass().getClassLoader(),
                support.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 代理执行");
        Class<?> aClass = support.getTargetSource().getTarget().getClass();
        Class<?>[] targetClass = support.getTargetSource().getTargetClass();
        MethodMatcher methodMatcher = support.getMethodMatcher();

        if(isSupportMethod(method) || support.getMethodMatcher().mathes(method, support.getTargetSource().getTarget().getClass())){
            // 如果方法匹配了 那么就执行
            MethodInterceptor methodInterceptor = support.getInterceptor();
            // 将原来的方法传了进去
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(support.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(support.getTargetSource().getTarget(), args);
    }

    private boolean anotherSatisfy(Method method){
        boolean assignableFrom = method.getClass().isAssignableFrom(support.getTargetSource().getTarget().getClass());
        return assignableFrom;
    }

    private boolean isSupportMethod(Method method){
        String methodName = method.getName();
        int parameterCount = method.getParameterCount();
        for(Method mt : this.support.getTargetSource().getClass().getDeclaredMethods()){
            String mtName = mt.getName();
            int parameterCount1 = mt.getParameterCount();
            if(methodName.equals(mtName) && parameterCount1==parameterCount1){
                return true;
            }
        }
        return false;

    }
}
