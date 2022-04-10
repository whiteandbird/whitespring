package com.wang.test.proxy;

import com.wang.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:41
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("ppppppppppppppppppp");
        System.out.println("拦截方法"+method.getName());
        try {
            Object invoke = method.invoke(target, args);
            System.out.println("结果"+invoke);
        }catch (Exception e){

        }

    }
}
