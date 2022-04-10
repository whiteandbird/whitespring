package com.wang.test;

import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.config.BeanFactoryPostProcessor;
import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;
import com.wang.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:09  16:03
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition person = beanFactory.getBeanDefinition("arm");
        person.getPropertyValues().addPropertieValue(new PropertyValue("num", 5));
    }
}
