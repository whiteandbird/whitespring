package com.wang.test;

import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.test.entity.Person;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  15:50
 */
public class MyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        if("person".equals(beanName)){
            ((Person)bean).setPersonName("newBeanName");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
