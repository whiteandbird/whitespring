package com.wang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.wang.springframework.beans.config.BeanReference;
import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.PropertyValue;
import com.wang.springframework.beans.PropertyValues;
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

        Object res = null;
        try{
            res = createBeanInstance(beanName, definition,  args);

            // 填充属性
            applyPropertyValues(beanName, res, definition);

            addSingleton(beanName, res);
        }catch (Exception e){
            e.printStackTrace();
            throw new BeansException(e.getMessage());
        }
        return res;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition definition) {
        PropertyValues propertyValues = definition.getPropertyValues();
        PropertyValue[] propertieVaules = propertyValues.getPropertieVaules();
        for(PropertyValue property : propertieVaules){
            String propertyName = property.getName();
            Object value = property.getValue();

            if(value instanceof BeanReference){
                // BeanReference类型去bean容器里面再拿·
                value = getBean(((BeanReference)value).getValue());
            }
            BeanUtil.setFieldValue(bean, propertyName, value);
        }
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
