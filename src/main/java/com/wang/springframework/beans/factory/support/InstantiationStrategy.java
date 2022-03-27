package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  16:32
 */
public interface InstantiationStrategy {
    Object instantiate(String beanName, BeanDefinition definition, Constructor constructor, Object[] args) throws BeansException;
}
