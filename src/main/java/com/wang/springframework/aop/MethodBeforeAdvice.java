package com.wang.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:11
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target);
}
