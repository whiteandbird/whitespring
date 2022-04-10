package com.wang.springframework.beans.factory.support;

import com.wang.springframework.BeanFactory;
import com.wang.springframework.beans.config.BeanPostProcessor;
import com.wang.springframework.beans.config.ConfigurableBeanFactory;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  13:12
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegisterSupport implements ConfigurableBeanFactory {


    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        beanPostProcessors.remove(postProcessor);
        beanPostProcessors.add(postProcessor);
    }

    @Override
    public Object getBean(String beanName) {
//        Object singleton = getSingleton(beanName);
//        if(singleton != null){
//            return singleton;
//        }
//
//        BeanDefinition beanDefinition = getBeanDefinition(beanName);
//        return createBean(beanName, beanDefinition, new Object[]{});
        return getBean(beanName, new Object[]{});
    }

    @Override
    public Object getBean(String beanName, Object... args) {
//        Object singleton = getSingleton(beanName);
////        if(singleton != null){
////            return singleton;
////        }
////
////        BeanDefinition beanDefinition = getBeanDefinition(beanName);
//        return createBean(beanName, beanDefinition, args);
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(String beanName,Object[] args){
        Object shareInstance = getSingleton(beanName);
        if(shareInstance != null){
            return (T) getObjectForBeanInstance(shareInstance, beanName);
        }
        // 单例里面没有 那么就要调用创建方法
        shareInstance = createBean(beanName, getBeanDefinition(beanName), args);
        return (T) getObjectForBeanInstance(shareInstance, beanName);
    }

    private Object getObjectForBeanInstance(Object bean,String beanName){
        if(! (bean instanceof FactoryBean)){
            return bean;
        }
        Object factoryBean = getCachedForFactoryBean(beanName);
        if(null == factoryBean){
            FactoryBean factoryBeanInstance = (FactoryBean) bean;
            factoryBean = getObjectFromFactoryBean(factoryBeanInstance, beanName);
        }
        return factoryBean;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> type) {
        return (T) getBean(beanName);
    }

    protected abstract Object createBean(String beanName, BeanDefinition definition, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract boolean containBeanDefintion(String beanName);


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
