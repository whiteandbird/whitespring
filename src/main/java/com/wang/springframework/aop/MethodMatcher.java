package com.wang.springframework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {
    boolean mathes(Method method,Class<?> clzz);
}
