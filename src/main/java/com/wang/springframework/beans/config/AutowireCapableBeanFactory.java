package com.wang.springframework.beans.config;

import com.wang.springframework.BeanFactory;

/**
 * 自动话处理bean工厂配置的接口
 * @author wangdy
 * @date 2022/3/31 20:32
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorAfterInitialize(String beanName, Object res);

    Object applyBeanPostProcessorBeforeInitialize(String beanName, Object res);

}
