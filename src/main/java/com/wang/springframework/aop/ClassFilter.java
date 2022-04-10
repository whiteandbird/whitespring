package com.wang.springframework.aop;

public interface ClassFilter {
    boolean matches(Class<?> clzz);
}
