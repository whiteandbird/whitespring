package com.wang.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:07
 */
public interface Advisor {
   Advice getAdvice();
}
