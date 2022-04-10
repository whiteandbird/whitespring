package com.wang.springframework.aop;

public interface PointCut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
