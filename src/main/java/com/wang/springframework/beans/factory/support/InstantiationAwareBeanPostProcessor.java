package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.beans.factory.aware.BeanFactoryAware;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  17:03
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor, BeanFactoryAware {
    Object postProcessorBeforeInstantiation(Class<?> beanClass,String beanName);
}
