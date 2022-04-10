package com.wang.springframework.context.support;

import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.context.ApplicationContext;
import com.wang.springframework.context.aware.ApplicationContextAware;
import lombok.Data;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  9:36
 */
@Data
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    public ApplicationContextAwareProcessor(ApplicationContext context){
        this.context = context;
    }

    private ApplicationContext context;

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplication(context);
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
