package com.wang.springframework.aop;

import com.wang.springframework.aop.MethodMatcher;
import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  15:37
 */
@Data
public class AdvisedSupport {
    private boolean proxyTargetClass = true;


    private TargetSource targetSource;

    private MethodInterceptor interceptor;

    private MethodMatcher methodMatcher;
}
