package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Constructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  16:38
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(String beanName, BeanDefinition definition, Constructor constructor, Object[] args) throws BeansException {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(definition.getBeanClass());
        enhancer.setCallback(new Callback() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(constructor != null){
            return enhancer.create(constructor.getParameterTypes(), args);
        }
        return enhancer.create();
    }
}
