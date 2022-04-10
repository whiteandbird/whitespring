package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;

import java.util.HashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:04:10  11:15
 */
public class FactoryBeanRegisterSupport extends DefaultSingletonBeanRegistry{
    private HashMap<String, Object> factoryBeanCache = new HashMap<>();

    public Object getCachedForFactoryBean(String beanName){
        return factoryBeanCache.get(beanName);
    }

    public Object getObjectFromFactoryBean(FactoryBean factoryBean,String beanName){
        if(factoryBean.singleton()){
            Object bean = getCachedForFactoryBean(beanName);
            if(null == bean){
                bean = doGetObjectFromFactoryBean(factoryBean, beanName);
                factoryBeanCache.put(beanName, bean);
            }
            return bean;
        }else
        {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName){
        try{
            return factoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            throw new BeansException("do get bean error");
        }
    }
}
