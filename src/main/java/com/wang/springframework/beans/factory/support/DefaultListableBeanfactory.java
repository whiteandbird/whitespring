package com.wang.springframework.beans.factory.support;

import com.wang.springframework.beans.BeansException;
import com.wang.springframework.beans.config.ConfigurableListableBeanFactory;
import com.wang.springframework.beans.factory.config.BeanDefinition;

import java.util.*;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  15:08
 */
public class DefaultListableBeanfactory extends AbstractAutowireCapableBeanfactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private HashMap<String, BeanDefinition> beanDefinitionHashMap = new HashMap<>();


    @Override
    public <T> List<T> getBeansOfType(Class<T> type) {
        List<T> res = new ArrayList<>();
        beanDefinitionHashMap.forEach((name, definition)->
        {
            if(type.isAssignableFrom(definition.getBeanClass())){
                T bean = (T)getBean(name);
                res.add(bean);
            }
        });
        return res;
    }

    @Override
    public String[] getBeansDefinitionNames() {
        Set<String> strings = beanDefinitionHashMap.keySet();
        return strings.toArray(new String[strings.size()]);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        List<String> names = new ArrayList<>();
        beanDefinitionHashMap.forEach((name, defintion)->{
            if(requiredType.isAssignableFrom(defintion.getBeanClass())){
                names.add(name);
            }
        });
        if(names.size() > 1){
            throw new BeansException("存在多个可选择bean");
        }
        return getBean(names.get(0), requiredType);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionHashMap.get(beanName);
        if(beanDefinition == null){
            throw new BeansException("no this beanDefintion");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() {
        beanDefinitionHashMap.forEach((name,definition)->{
            if(definition.isSingleton()){
                getBean(name);
            }
        });
    }

    @Override
    protected boolean containBeanDefintion(String beanName) {
        return beanDefinitionHashMap.containsKey(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition definition) {
        beanDefinitionHashMap.put(beanName, definition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionHashMap.containsKey(beanName);
    }
}
