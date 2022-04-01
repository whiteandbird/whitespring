package com.wang.springframework.beans.config;

/**
 * @author wangdy
 * @date 2022/3/31 20:06
 */
public interface BeanFactoryPostProcessor {
    /**
     * bean工厂后置处理器
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
