package com.wang.test;

import com.wang.springframework.beans.config.BeanPostProcessor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  10:17
 */
public class MyProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
