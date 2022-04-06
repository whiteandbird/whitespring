package com.wang.springframework.beans.config;

/**
 * @author wangdy
 * @date 2022/3/31 20:06
 */
public interface BeanPostProcessor {
    Object postProcessorBeforeInitialization(Object bean,String beanName);

    Object postProcessorAfterInitialization(Object bean,String beanName);
}
