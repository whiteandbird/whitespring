package com.wang.springframework.aop;

public interface PointcutAdvisor extends Advisor{
    PointCut getPointCut();
}
