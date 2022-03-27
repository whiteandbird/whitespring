package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  15:08
 */
public class DefaultListableBeanfactory extends AbstractAutowireCapableBeanfactory implements BeanDefinitionRegistry {

    private HashMap<String, BeanDefinition> beanDefinitionHashMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionHashMap.get(beanName);
        if(beanDefinition == null){
            throw new BeansException("no this beanDefintion");
        }
        return beanDefinition;
    }

    @Override
    protected boolean containBeanDefintion(String beanName) {
        return beanDefinitionHashMap.containsKey(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition definition) {
        beanDefinitionHashMap.put(beanName, definition);
    }
}
