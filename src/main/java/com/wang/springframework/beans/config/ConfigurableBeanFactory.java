package com.wang.springframework.beans.config;

import com.wang.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * 获取BeanPostProcessor BeanClassLoader的一个配置化接口
 * @author wangdy
 * @date 2022/3/31 21:53
 */
public interface ConfigurableBeanFactory extends HierachicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();
}
