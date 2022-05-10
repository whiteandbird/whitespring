package com.wang.springframework.aop;

import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  16:03
 */
@NoArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {
    private Object target;

    protected Method method;

    private Object[] arguments;


    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        System.out.println("reflect 返回");
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
