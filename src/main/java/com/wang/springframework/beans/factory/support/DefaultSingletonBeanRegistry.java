package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  13:15
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    @Override
    public void addSingleton(String beanName, Object bean) {
        singletonMap.put(beanName, bean);
    }
}
