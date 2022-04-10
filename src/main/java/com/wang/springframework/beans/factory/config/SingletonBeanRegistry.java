package com.wang.springframework.beans.factory.config;

import com.wang.springframework.context.support.DestroyableBean;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  13:03
 */
public interface SingletonBeanRegistry {

    /**
     * get single bean
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    void addSingleton(String beanName,Object bean);

    void addDestroyBean(String beanName, DestroyableBean destroyableBean);
}
