package com.wang.springframework.beans.factory.support;

import com.wang.springframework.BeanFactory;
import com.wang.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  13:12
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        Object singleton = getSingleton(beanName);
        if(singleton != null){
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, new Object[]{});
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        Object singleton = getSingleton(beanName);
        if(singleton != null){
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition definition,Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract boolean containBeanDefintion(String beanName);

}
