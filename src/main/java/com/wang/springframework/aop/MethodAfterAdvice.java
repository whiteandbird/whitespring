package com.wang.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author wangdy
 * @date 2022/5/18 9:16
 */
public interface MethodAfterAdvice extends AfterAdvice{
    void after(Method method,Object[] args,Object target);
}
