package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  16:35
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(String beanName, BeanDefinition definition, Constructor constructor, Object[] args) throws BeansException {
        Class beanClass = definition.getBeanClass();
        try{
            if (constructor !=null){
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
            return beanClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            throw new BeansException(beanName+"创建失败");
        }
    }
}
