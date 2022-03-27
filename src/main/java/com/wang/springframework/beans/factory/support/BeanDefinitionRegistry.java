package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.factory.config.BeanDefinition;


public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName , BeanDefinition definition);
}
