package com.wang.springframework.beans.config;


import com.wang.springframework.beans.factory.config.BeanDefinition;

/**
 * 提供分析和修改bean以及预先实例化的接口
 * @author wangdy
 * @date 2022/3/31 20:29
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory,ConfigurableBeanFactory, AutowireCapableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);

    void preInstantiateSingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
