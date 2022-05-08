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

    /**
     * 该方法通过判断bean的类型来决定是否返回普通的或者factoryBean里面的数据
     * @param beanName
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T doGetBean(String beanName,Object[] args){
        Object shareInstance = getSingleton(beanName);
        // 拿到bean 但是首先得给factoryBean一个机会  让其有机会
        if(shareInstance != null){
            // 给factoryBean一个机会
            return (T) getObjectForBeanInstance(shareInstance, beanName);
        }
        // 单例里面没有 那么就要调用创建方法
        shareInstance = createBean(beanName, getBeanDefinition(beanName), args);
        // 创建完了还是得给factoryBean一个机会
        return (T) getObjectForBeanInstance(shareInstance, beanName);
    }

    private Object getObjectForBeanInstance(Object bean,String beanName){
        if(! (bean instanceof FactoryBean)){
            return bean;
        }
        // 判断为factoryBean 那么需要执行获取factoryBean对象 先从factoryBean缓存里面拿
        Object factoryBean = getCachedForFactoryBean(beanName);
        if(null == factoryBean){
            // 缓存里面没有 那么就得去生成
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
