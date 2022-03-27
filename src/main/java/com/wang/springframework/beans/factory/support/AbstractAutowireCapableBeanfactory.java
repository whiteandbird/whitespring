package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  14:55
 */
public abstract class AbstractAutowireCapableBeanfactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition, Object[] args) {

        Object o = createBeanInstance(beanName, definition,  args);

        addSingleton(beanName, o);
        return o;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition definition, Object[] args)throws  RuntimeException{
        Class beanClass = definition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor constructor = null;
        for(Constructor item : declaredConstructors){
            if(item.getParameterTypes().length == args.length){
                constructor = item;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanName, definition, constructor, args);
    };
}
